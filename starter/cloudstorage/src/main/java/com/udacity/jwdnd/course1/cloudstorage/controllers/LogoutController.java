package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/logout")
@Controller
public class LogoutController {
    @GetMapping
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/login";
    }
}
