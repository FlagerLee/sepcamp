package com.seclass.sepcamp.daos;

import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.models.UserRegister;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM SEPCAMP_USER WHERE EMAIL = #{email}")
    User getUserByEmail(String email);

    @Select("SELECT * FROM SEPCAMP_USER WHERE USERNAME = #{username}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO SEPCAMP_USER(EMAIL, USERNAME, PASSWORD, PRIORITY, AVATAR) " +
            "VALUE(#{email}, #{username}, #{password}, 1, \"IGNORE\")")
    int register(UserRegister register);
}
