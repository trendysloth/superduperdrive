package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Insert("INSERT INTO CREDENTIALS (url, username, password, userId) " +
            "VALUES(#{credential.url}, #{credential.username}, #{credential.password}, #{credential.userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer save(@Param("credential") Credentials credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credentials> findCredentialsByUserId(@Param("userId") Integer userId);
}
