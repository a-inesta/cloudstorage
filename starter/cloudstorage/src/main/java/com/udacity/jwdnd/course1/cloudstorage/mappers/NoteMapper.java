package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> selectNotesByUserid(@Param("userid") Integer userid);


    @Select("SELECT * FROM NOTES WHERE noteid = #{id}")
    Note selectNoteById(@Param("id") Integer id);

    @Select("SELECT * FROM NOTES")
    List<Note> selectAllNotes();

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid)" +
            "VALUES (#{notetitle}, #{notedescription}, #{userid})")
    int insertNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{id}")
    int deleteNote(@Param("id") Integer id);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription}" +
            "WHERE noteid = #{noteid}")
    int updateNote(Note note);

    @Select("SELECT userid FROM NOTES WHERE noteid = #{noteid}")
    int selectSpecificUserid(Integer noteid);
}
