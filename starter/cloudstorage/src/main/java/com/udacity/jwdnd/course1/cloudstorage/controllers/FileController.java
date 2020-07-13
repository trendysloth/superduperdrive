package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;

import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class FileController {
    private NoteService noteService;
    private UserService userService;
    private FileService fileService;

    public FileController(NoteService noteService, UserService userService, FileService fileService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping("/file-upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload,
                             Authentication auth,
                             Model model) throws IOException {
        User user = this.userService.getUser(auth.getName());
        this.fileService.uploadFile(fileUpload, user.getUserid());
        model.addAttribute("files", this.fileService.getAllFiles(user.getUserid()));
        model.addAttribute("notes", this.noteService.getAllNotes(user.getUserid()));
        return "home";
    }

    @RequestMapping("/file/view/{fileid}")
    public ResponseEntity downloadFile(@PathVariable("fileid") Integer fileId,
                                       Authentication auth,
                                       Model model) throws IOException {
        Files file = this.fileService.getFileById(fileId);
        String contentType = file.getContentType();
        String fileName = file.getFileName();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file.getFileData());
    }

    @RequestMapping("/file-delete/{fileid}")
    public String deleteFile(@PathVariable("fileid") Integer fileId,
                             Authentication auth,
                             Model model) throws IOException {
        fileService.deleteFile(fileId);
        User user = this.userService.getUser(auth.getName());
        model.addAttribute("files", this.fileService.getAllFiles(user.getUserid()));
        model.addAttribute("notes", this.noteService.getAllNotes(user.getUserid()));
        return "home";
    }
}