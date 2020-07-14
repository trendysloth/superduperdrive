package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class CredentialController {
    private NoteService noteService;
    private UserService userService;
    private FileService fileService;
    private CredentialService credentialService;

    public CredentialController(NoteService noteService, UserService userService,
                                FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @PostMapping("/credential-upload")
    public String uploadCredential(@RequestParam("credentialId") String credentialId,
                                   @RequestParam("url") String credentialUrl,
                                   @RequestParam("username") String credentialUsername,
                                   @RequestParam("password") String credentialPassword,
                                   Authentication auth,
                                   Model model) throws IOException {
//        System.out.println(credentialId + " " + credentialUrl + " " + credentialUsername + " " + credentialPassword);
//        System.out.println(credentialId == null);
        User user = this.userService.getUser(auth.getName());
//        if (credentialId == null) {
//
//        } else {
////            this.noteService.updateNote(noteId, noteTitle, noteDescription, user.getUserid());
//        }
        this.credentialService.uploadCredential(credentialUrl, credentialUsername, credentialPassword, user.getUserid());
        model.addAttribute("files", this.fileService.getAllFiles(user.getUserid()));
        model.addAttribute("notes", this.noteService.getAllNotes(user.getUserid()));
        model.addAttribute("credentials", this.credentialService.getAllCredentials(user.getUserid()));
        return "home";
    }
}
