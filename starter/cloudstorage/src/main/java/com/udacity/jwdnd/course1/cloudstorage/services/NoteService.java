package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    NoteMapper noteMapper;


    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Note selectNote(Integer id) {
        return noteMapper.selectNoteById(id);
    }

    public int addNote(NoteForm noteForm, Integer userid) {
        int x;
        if(noteForm.getNoteId() == null) {
            x = noteMapper.insertNote(new Note(noteForm.getNoteId(),
                    noteForm.getNoteTitle(),
                    noteForm.getNoteDescription(),
                    userid));
        } else {
            x = editNote(noteForm, userid);
        }
        return x;
    }

    public int editNote(NoteForm noteForm, Integer userid) {
        return noteMapper.updateNote(new Note(noteForm.getNoteId(),
                noteForm.getNoteTitle(),
                noteForm.getNoteDescription(),
                userid));
    }

    public boolean deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
        return noteMapper.selectNoteById(noteId) == null;
    }

    public List<Note> getAllNotes(Integer userid) {
        return noteMapper.selectNotesByUserid(userid);
    }

}
