package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {


    @Insert("INSERT INTO USERS (userid, username, salt, password, firstname, lastname) " +
            "VALUES(#{userid}, #{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    int addUser(User user);

    @Update("UPDATE USERS SET username = #{username}, salt = #{salt}, password = #{password}, firstname = #{firstname}, " +
            "lastname = #{lastname} WHERE userid = #{userid}")
    int updateUser(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{userid}")
    int deleteUser(Integer userid);

    @Select("SELECT * FROM USERS WHERE userid = #{userid}")
    User selectUserById(Integer userid);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User selectUserByName(String username);
}
