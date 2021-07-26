package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    NoteMapper noteMapper;
    UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public Note selectNote(Integer id) {
        return noteMapper.selectNoteById(id);
    }

    public int addNote(NoteForm noteForm, String username) {
        int x;
        if(noteForm.getNoteId() == null) {
            x = noteMapper.insertNote(new Note(noteForm.getNoteId(),
                    noteForm.getNoteTitle(),
                    noteForm.getNoteDescription(),
                    userService.getUser(username).getUserid()));
        } else {
            x = editNote(noteForm, username);
        }
        return x;
    }

    public int editNote(NoteForm noteForm, String username) {
        return noteMapper.updateNote(new Note(noteForm.getNoteId(),
                noteForm.getNoteTitle(),
                noteForm.getNoteDescription(),
                userService.getUser(username).getUserid()));
    }

    public int deleteNote(Integer noteId) {
        return noteMapper.deleteNote(noteId);
    }

    public List<Note> getAllNotes() {
        return noteMapper.selectAllNotes();
    }

}
