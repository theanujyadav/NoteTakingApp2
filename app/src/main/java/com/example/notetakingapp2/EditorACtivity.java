package com.example.notetakingapp2;

import android.os.Bundle;

import com.example.notetakingapp2.Utils.Constants;
import com.example.notetakingapp2.ViewModels.EditorViewModel;
import com.example.notetakingapp2.database.NoteEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditorACtivity extends AppCompatActivity {
    private EditorViewModel mViewModel;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        My Code
        editText = findViewById(R.id.edit_text);
        initViewModel();

    }

    private void initViewModel() {
        Observer<NoteEntity> noteObserser = new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {


                if(noteEntity !=null){
                    editText.setText(noteEntity.getText());
                }


            }
        };

        mViewModel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);
            mViewModel.mLiveNote.observe(EditorACtivity.this,noteObserser);
    //Gettin intent data
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            setTitle("Add notes");
        }else {
            setTitle("Edit Notes");
            int id = bundle.getInt(Constants.NOTE_ID_KEY);
            mViewModel.LoadNotes(id);

        }

    }
        public boolean onOptionsItemSelected(MenuItem item){
            if (item.getItemId() == android.R.id.home){
                Log.d(MainActivity.MYTAG,"save addes" + Thread.currentThread().toString());
                mViewModel.saveAndExit(editText.getText().toString());
            }
        return super.onOptionsItemSelected(item);
        }



}
