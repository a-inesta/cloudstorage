package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cred")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addCredential(CredentialForm credForm, Authentication au, Model model) {
        if (credentialService.addCredential(credForm,
                userService.getUser(au.getName()).getUserid()) == 1) {
            model.addAttribute("success","Credentials added successfully!");
        } else {
            model.addAttribute("error","Failed to add credentials!");
        }
        return "result";
    }

    @RequestMapping("/delete/{credid}")
    public String deleteCredential(@PathVariable Integer credid, Model model) {
        if (credentialService.deleteCredential(credid)) {
            model.addAttribute("success","Credentials deleted successfully!");
        } else {
            model.addAttribute("error","Failed to delete credentials!");
        }
        return "result";
    }


}
