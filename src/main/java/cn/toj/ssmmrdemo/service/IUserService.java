package cn.toj.ssmmrdemo.service;

import cn.toj.ssmmrdemo.dto.LoginInformationDto;
import cn.toj.ssmmrdemo.dto.UserDto;

import java.util.Map;

public interface IUserService {

    /**
     * 注册用户
     * @param userDto
     */
    void registerUser(UserDto userDto);

    /**
     * 登录验证是否已经注册
     * @param dto
     * @return
     */
    Map<String, UserDto> Login(LoginInformationDto dto);

    /**
     * 退出登录
     * @param dto
     */
    void logOut(LoginInformationDto dto);

}
