package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {
    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping("/add")
    public String addNote(NoteForm noteForm, Model model, Authentication au) {
        if (noteService.addNote(noteForm,au.getName()) == 1) {
            model.addAttribute("success", "操作成功");
        } else {
            model.addAttribute("error", "笔记添加失败");
        }
        return "result";
    }

    @RequestMapping("/edit")
    public String editNote( NoteForm noteForm, Model model, Authentication au) {
        if (noteService.editNote(noteForm,au.getName()) == 1) {
            model.addAttribute("success", "笔记修改成功");
        } else {
            model.addAttribute("error", "笔记修改失败");
        }
        return "result";
    }

    @RequestMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {
        noteService.deleteNote(noteId);
        if (noteService.selectNote(noteId) == null)
        model.addAttribute("success", "笔记删除成功");
        return "result";
    }
}
