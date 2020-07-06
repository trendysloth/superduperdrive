package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("INSERT INTO FILE (fileName, contentType, fileSize, fileData, userId) VALUES " +
            "(#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId}")
    Integer uploadFile(@Param("file") Files file);

    @Select("SELECT * FROM FILE WHERE userId = #{userId}")
    List<Files> findFilesByUserId(@Param("userId") Integer userId);

    @Delete("DELETE * FROM FILE WHERE fileId = #{fileId}")
    void deleteById(@Param("fileId") Integer fileId);

    @Select("SELECT * FROM FILE WHERE fileId = #{fileId}")
    Files findById(@Param("fileId") Integer fileId);
}