package com.example.notetakingapp2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities = {NoteEntity.class},version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "notesdatabase.db";
    public static volatile AppDatabase instance;

    private static final Object LOCK = new Object();
    public abstract NotesDao notesDao();

public static AppDatabase getInstance(Context context){
    if (instance == null){
        synchronized (LOCK){
            if (instance == null){
                instance= Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,DATABASE_NAME).build();
            }
        }
    }
    return instance;
}


}
