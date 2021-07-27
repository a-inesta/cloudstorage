package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cred")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @RequestMapping("/add")
    public String addCredential(CredentialForm credForm, Authentication au, Model model) {
        if (credentialService.addCredential(credForm,
                userService.getUser(au.getName()).getUserid()) == 1) {
            model.addAttribute("success","操作成功");
        } else {
            model.addAttribute("error","添加凭证失败");
        }
        return "result";
    }

    @RequestMapping("/delete/{credid}")
    public String deleteCredential(@PathVariable Integer credid, Model model) {
        if (credentialService.deleteCredential(credid)) {
            model.addAttribute("success","删除成功");
        } else {
            model.addAttribute("error","删除失败");
        }
        return "result";
    }


}
