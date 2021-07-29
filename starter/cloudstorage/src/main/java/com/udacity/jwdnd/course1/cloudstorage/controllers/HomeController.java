package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    FileService fileService;
    UserService userService;
    NoteService noteService;
    CredentialService credentialService;

    public HomeController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String toHome(Model model, NoteForm noteForm, CredentialForm credentialForm, Authentication au) {
        int userid = userService.getUser(au.getName()).getUserid();
        //查出所有的Filename 并传给model显示
        List<File> allFiles = fileService.getAllFiles(userid);
        model.addAttribute("fileLists", allFiles);
        //查出所有的Notes 传给model显示
        List<Note> allNotes = noteService.getAllNotes(userid);
        model.addAttribute("noteLists", allNotes);
        //查出所有的Credentials，传给model显示
        List<CredentialForm> allCredentialsForm = credentialService.getAllCredentialForms(userid);
        model.addAttribute("credLists", allCredentialsForm);

        return "/home";
    }


}
