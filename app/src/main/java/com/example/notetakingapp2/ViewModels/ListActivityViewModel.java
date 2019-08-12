package com.example.notetakingapp2.ViewModels;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notetakingapp2.MainActivity;
import com.example.notetakingapp2.Utils.SampleDataProvider;
import com.example.notetakingapp2.database.AppDatabase;
import com.example.notetakingapp2.database.NoteEntity;
import com.example.notetakingapp2.database.NotesDao;

import java.util.List;

public class ListActivityViewModel extends AndroidViewModel {
    private AppDatabase mDatabase;
    public LiveData<List<NoteEntity>> mNotesList;
    private NotesDao notesDao;
    private Context context;
    int numberDataDeleted = 0;

    public ListActivityViewModel(@NonNull Application application) {
        super( application);
        context = application.getApplicationContext();
        mDatabase = AppDatabase.getInstance(context);
        notesDao= mDatabase.notesDao();

        mNotesList = getAllNotes();
        }

    public void addSampleData() {
        new InsertAsynkTask(notesDao).execute(SampleDataProvider.getSampleData());


    }
    public class InsertAsynkTask extends AsyncTask<List<NoteEntity> , Void, Void>{

        NotesDao notesDao;
        public InsertAsynkTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(List<NoteEntity>... lists) {
            Log.d(MainActivity.MYTAG,"Sata inserted");
                notesDao.insertAll(lists[0]);

            return null;
        }

    }
    public void deleteAllData() {
               new DeleteAsyncTask(notesDao).execute(SampleDataProvider.getSampleData());
        Toast.makeText(context, numberDataDeleted + "Data Delted",Toast.LENGTH_LONG).show();
    }

    public class DeleteAsyncTask extends AsyncTask<List<NoteEntity>, Void, Void>{
        NotesDao notesDao;
        public DeleteAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(List<NoteEntity>... lists) {
             numberDataDeleted = notesDao.deleteAllNotes();

            return null;
        }
    }

    public LiveData<List<NoteEntity>>   getAllNotes(){

        return notesDao.getAllNotes();
    }
}


