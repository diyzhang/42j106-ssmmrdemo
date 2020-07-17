package cn.toj.ssmmrdemo.dto;

import java.io.Serializable;

/**
 * @author Carlos
 * @description 登录用户类
 * @Date 2020/7/15
 */

public class LoginInformationDto implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
