package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class NoteController {
    private NoteService noteService;
    private UserService userService;
    private FileService fileService;
    private CredentialService credentialService;

    public NoteController(NoteService noteService, UserService userService, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @PostMapping("/note-upload")
    public String uploadFile(@RequestParam("noteTitle") String noteTitle,
                             @RequestParam("noteDescription") String noteDescription,
                             @RequestParam("noteId") Integer noteId,
                             Authentication auth,
                             Model model) throws IOException {
        User user = this.userService.getUser(auth.getName());
        if (noteId == null) {
            this.noteService.uploadNote(noteTitle, noteDescription, user.getUserid());
        } else {
            this.noteService.updateNote(noteId, noteTitle, noteDescription, user.getUserid());
        }
        model.addAttribute("files", this.fileService.getAllFiles(user.getUserid()));
        model.addAttribute("notes", this.noteService.getAllNotes(user.getUserid()));
        model.addAttribute("credentials", this.credentialService.getAllCredentials(user.getUserid()));
        return "home";
    }

    @RequestMapping("/note-delete/{noteId}")
    public String deleteFile(@PathVariable("noteId") Integer noteId,
                             Authentication auth,
                             Model model) throws IOException {
        noteService.deleteNote(noteId);
        User user = this.userService.getUser(auth.getName());
        model.addAttribute("files", this.fileService.getAllFiles(user.getUserid()));
        model.addAttribute("notes", this.noteService.getAllNotes(user.getUserid()));
        model.addAttribute("credentials", this.credentialService.getAllCredentials(user.getUserid()));
        return "home";
    }
}
