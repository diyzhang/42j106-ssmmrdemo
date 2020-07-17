package cn.toj.ssmmrdemo.mapper;

import cn.toj.ssmmrdemo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 插入一条用户信息
     * @param user
     */
    @Insert("insert into user_ssm (username, password, sex, address) values(#{username}, #{password}, #{sex}, #{address})")
    void insertUser(User user);

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    @Select("select * from user_ssm where username = #{username}")
    User selectByUserName(@Param("username") String username);

}
