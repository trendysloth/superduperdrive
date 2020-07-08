package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES " +
            "(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    Integer save(@Param("file") Files file);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<Files> findFilesByUserId(@Param("userId") Integer userId);

    @Delete("DELETE * FROM FILES WHERE fileId = #{fileId}")
    void deleteById(@Param("fileId") Integer fileId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files findById(@Param("fileId") Integer fileId);
}