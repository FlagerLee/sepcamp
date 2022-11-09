package com.seclass.sepcamp.daos;

import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.models.UserRegister;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM SEPCAMP_USER WHERE EMAIL = #{email}")
    User getUserByEmail(String email);

    @Select("SELECT * FROM SEPCAMP_USER WHERE USERNAME = #{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM SEPCAMP_USER WHERE USER_ID = #{userId}")
    User getUserByUserId(int  userId);

    @Select("SELECT * FROM SEPCAMP_USER WHERE TEAM_ID = #{teamID}")
    List<User> getUserByTeamId(int  teamID);


    @Update("UPDATE SEPCAMP_USER SET TEAM_ID = #{teamId}  WHERE USER_ID = #{userId}")
    int updateTeamIdForUser(int  userId,int teamId);


    @Insert("INSERT INTO SEPCAMP_USER(EMAIL, USERNAME, PASSWORD, PRIORITY, AVATAR) " +
            "VALUE(#{email}, #{username}, #{password}, 1, \"IGNORE\")")
    int register(UserRegister register);
}
