package com.example.notetakingapp2.ViewModels;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.notetakingapp2.MainActivity;
import com.example.notetakingapp2.database.AppDatabase;
import com.example.notetakingapp2.database.NoteEntity;
import com.example.notetakingapp2.database.NotesDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {
    private AppDatabase mDatabase;
    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<>();
    Context context;
    NotesDao notesDao;

    NoteEntity noteEntity;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public EditorViewModel(@NonNull Application application) {
        super(application);
         context = application.getApplicationContext();
        mDatabase = AppDatabase.getInstance(context);
        notesDao= mDatabase.notesDao();
        mLiveNote.postValue(noteEntity);
        }


    public void LoadNotes(final int id) {

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
            Log.d(MainActivity.MYTAG,"Data loaded hah" + Thread.currentThread().toString());
                noteEntity = notesDao.getNoteById(id);
                mLiveNote.postValue(noteEntity);

            }
        });

    }


    public void saveAndExit(final String noteText) {
          noteEntity = mLiveNote.getValue();
        if (noteEntity == null){
            noteEntity = new NoteEntity(new Date(), noteText.trim());

        }else {
          noteEntity.setText(noteText.trim());
          noteEntity.setDate(new Date());
        }
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.InsertNote(noteEntity);
            }
        });
        }


}
