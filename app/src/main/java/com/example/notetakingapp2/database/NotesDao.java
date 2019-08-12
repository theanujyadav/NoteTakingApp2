package com.example.notetakingapp2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertNote(NoteEntity noteEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NoteEntity> noteEntities);

    @Delete
    void deleteNote(NoteEntity noteEntity);

    @Query("Select * FROM notes Where id = :id")
    NoteEntity getNoteById(int id);

    @Query("SELECT * FROM notes ORDER BY date DESC")
   LiveData<List<NoteEntity>>   getAllNotes();

    @Query("DELETE FROM notes")
    int deleteAllNotes();

    @Query("SELECT COUNT(*) FROM notes")
    int getCount();
}
