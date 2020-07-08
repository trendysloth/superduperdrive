package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    public Files uploadFile(MultipartFile multipartFile, Integer userId) throws IOException {
        Files newFile = new Files(
            null,
            multipartFile.getOriginalFilename(),
            multipartFile.getContentType(),
            multipartFile.getSize(),
            multipartFile.getBytes(),
            userId
        );
        try {
            fileMapper.save(newFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFile;
    }

    public List<Files> getAllFiles(Integer userId) {
        return fileMapper.findFilesByUserId(userId);
    }
}
