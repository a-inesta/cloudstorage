package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/note")
public class NoteController {
    NoteService noteService;
    UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addNote(NoteForm noteForm, Model model, Authentication au) {
        if (noteService.addNote(noteForm,userService.getUser(au.getName()).getUserid()) == 1) {
            model.addAttribute("success", "Note added successfully!");
        } else {
            model.addAttribute("error", "Note adding failed!");
        }
        return "result";
    }

    @RequestMapping("/edit")
    public String editNote( NoteForm noteForm, Model model, Authentication au) {
        if (noteService.editNote(noteForm,userService.getUser(au.getName()).getUserid()) == 1) {
            model.addAttribute("success", "Note modification succeeded!");
        } else {
            model.addAttribute("error", "Note modification failed!");
        }
        return "result";
    }

    @RequestMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model, Authentication au) {
        try {
            if(noteService.deleteNote(noteId, userService.getUser(au.getName()).getUserid())){
                model.addAttribute("success", "Note deleted successfully!");
            } else {
                model.addAttribute("error", "Note deletion failed");
            }
        } catch (IllegalAccessException e) {
            model.addAttribute("error", "illegal access");
        }
        return "/result";
    }
}
