package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{id}")
    Credential selectCredentialById(@Param("id") Integer id);

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> selectAllCredentials();

    @Insert("INSERT INTO CREDENTIALS (credentialid, url, username, key, password, userid)" +
            "VALUES (#{crendentials}, #{url}, #{username}, #{key}, #{password}, #{userid})")
    int insertCredential(Credential credential);

    @Delete("DELETE FROM CRENDENTIALS WHERE crendentialid = #{id}")
    int deleteCredential(@Param("id") Integer id);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}," +
            "password = #{password}, userid = #{userid}")
    int updateCredential(Credential credential);
}
