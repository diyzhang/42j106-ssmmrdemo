package cn.toj.ssmmrdemo.controller;

import cn.toj.ssmmrdemo.dto.LoginInformationDto;
import cn.toj.ssmmrdemo.dto.UserDto;
import cn.toj.ssmmrdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Carlos
 * @description 用户控制类
 * @Date 2020/7/15
 */

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/insert")
    public String registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return "Register User Success.";
    }

    @PostMapping("/login")
    public Map<String, UserDto> loginUser(@RequestBody LoginInformationDto dto) {

        return userService.Login(dto);

    }

    @PostMapping("/logout")
    public String logOut(@RequestBody LoginInformationDto dto) {
        userService.logOut(dto);
        return "Logout Success.";
    }

}
