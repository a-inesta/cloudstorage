package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    FileService fileService;
    UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Model model, Authentication authentication) {
        String filename = multipartFile.getOriginalFilename();
        if (!fileService.isFileNameAvailable(filename)) {
            model.addAttribute("error","文件名重复");
            return "result";
        }

        try {
            fileService.addFile(new File(null, filename,
                    multipartFile.getContentType(),
                    multipartFile.getSize() + "",
                    userService.getUser(authentication.getName()).getUserid(),
                    multipartFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("success","true");
        return "result";
    }

    @RequestMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        int row = fileService.deleteFile(fileId);
        if (row > 1) {
            model.addAttribute("success","true");
        }
        return "/result";
    }

    @RequestMapping("/view")
    public String viewFile(Model model) {
        return "/result";
    }
}
