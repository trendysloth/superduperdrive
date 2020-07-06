package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/file-upload", method = RequestMethod.POST)
    public String uploadFile(@ModelAttribute("SpringWeb") MultipartFile fileUpload,
                             HttpSession session, ModelMap model) throws IOException {
        User user = (User) session.getAttribute("user");
        this.fileService.uploadFile(fileUpload, user.getUserid());
        model.addAttribute("files", this.fileService.getAllFiles(user.getUserid()));
        return "home";
    }
}
