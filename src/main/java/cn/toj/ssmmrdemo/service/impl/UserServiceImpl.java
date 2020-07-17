package cn.toj.ssmmrdemo.service.impl;

import cn.toj.ssmmrdemo.domain.User;
import cn.toj.ssmmrdemo.dto.LoginInformationDto;
import cn.toj.ssmmrdemo.dto.UserDto;
import cn.toj.ssmmrdemo.mapper.UserMapper;
import cn.toj.ssmmrdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carlos
 * @description 用户服务实现类
 * @Date 2020/7/15
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void registerUser(UserDto userDto) {

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setSex(userDto.getSex());
        user.setAddress(userDto.getAddress());
        userMapper.insertUser(user);

    }

    @Override
    public Map<String, UserDto> Login(LoginInformationDto dto) {

        User user = userMapper.selectByUserName(dto.getUsername());
        HashMap<String, UserDto> dtoHashMap = new HashMap<>();

        if (user == null) {

            dtoHashMap.put("Login Fail, the user doesn't exist in this database", null);
        } else {

            if (user.getPassword().equals(dto.getPassword())) {

                //以下为修改部分
                //1. 连接Redis
                Jedis jedis = new Jedis("localhost", 6379);

                //2.1 如果Redis中存在用户，则返回用户已经登录
                if (jedis.exists(user.getUsername())) {
                    dtoHashMap.put("The user is logged in.", null);
                }else {
                //2.2 否则则走正常登录程序并在Redis中存入用户信息，表明用户已登录
                    UserDto userDto = new UserDto();
                    userDto.setUsername(user.getUsername());
                    userDto.setSex(user.getSex());
                    userDto.setAddress(user.getAddress());
                    dtoHashMap.put("Login success", userDto);

                    jedis.set(user.getUsername(), user.getPassword());
                }
                //修改内容完毕


            } else {
                dtoHashMap.put("User password error, please try again.", null);
            }
        }

        return dtoHashMap;

    }

    @Override
    public void logOut(LoginInformationDto dto) {

        Jedis jedis = new Jedis("localhost", 6379);

        jedis.del(dto.getUsername());


    }
}
