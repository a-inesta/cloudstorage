package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> selectFilesByUserid(@Param("userid") Integer userid);


    @Select("SELECT * FROM FILES")
    List<File> selectAllFiles();

    @Select("SELECT * FROM FILES WHERE fileId = #{id}")
    File selectFileById(@Param("id") Integer id);

    @Insert("INSERT INTO FILES (fileId, filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{fileId}, #{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{id}")
    int deleteFile(@Param("id") Integer id);

    @Update("UPDATE FILES SET filename = #{filename}, contenttype = #{contenttype}," +
            "filesize = #{filesize}, userid = #{userid}, filedata = #{filedata} WHERE fileid = #{fileid}")
    int updateFile(File file);

    @Select("SELECT filename FROM FILES")
    List<String> selectAllFilenames();

    @Select("SELECT * FROM FILES WHERE userid = #{userid} AND filename = #{filename} ")
    File selectFileByFilename(String filename, Integer userid);
}
