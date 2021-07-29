package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

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
        Integer userid = userService.getUser(authentication.getName()).getUserid();
        String error = null;
        if (!fileService.isFileNameAvailable(filename, userid)) {
            error = "duplicate file name!";
        }
        if (error == null) {
            try {
                fileService.addFile(new File(null, filename,
                        multipartFile.getContentType(),
                        multipartFile.getSize() + "",
                        userid,
                        multipartFile.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
                error = "IOException:" + e.getLocalizedMessage();
            }
        }

        if (error == null) {
            model.addAttribute("success","The file was uploaded successfully!");
        } else {
            model.addAttribute("error", error);
        }
        return "result";
    }

    @RequestMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        if (fileService.deleteFile(fileId)) {
            model.addAttribute("success","File deletion succeeded!");
        } else {
            model.addAttribute("error", "File deletion failed!");
        }
        return "/result";
    }

    @RequestMapping("/view/{fileId}")
    @ResponseBody
    public String viewFile(@PathVariable Integer fileId,OutputStream os, HttpServletResponse response) {
        File file = fileService.getFileById(fileId);
        String fileName = file.getFilename();
        byte[] fileNameBytes = fileName.getBytes(StandardCharsets.UTF_8);
        fileName = new String(fileNameBytes, 0, fileNameBytes.length, StandardCharsets.ISO_8859_1);
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength(file.getFiledata().length);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        try {
            os.write(file.getFiledata());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return "File download failed!";
        }

        return "File downloaded successfully!";
    }
}
