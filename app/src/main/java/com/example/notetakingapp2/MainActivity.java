package com.example.notetakingapp2;

import android.content.Intent;
import android.os.Bundle;

import com.example.notetakingapp2.Model.NoteAdaptor;
import com.example.notetakingapp2.ViewModels.ListActivityViewModel;
import com.example.notetakingapp2.database.NoteEntity;
import com.example.notetakingapp2.Utils.SampleDataProvider;
import com.example.notetakingapp2.database.NotesDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String MYTAG = "MyTag";
    RecyclerView recyclerView;
    private List<NoteEntity> mNoteList = new ArrayList<>();
    private ListActivityViewModel mViewModel;
    NoteAdaptor noteAdaptor;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this,EditorACtivity.class);
              startActivity(intent);
            }
        });
//        My code


         initViewModel();

//        Recycler View
           recyclerView = findViewById(R.id.note_recyclerView);
           recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initViewModel() {
           Observer<List<NoteEntity>> noteObserver =
                   new Observer<List<NoteEntity>>() {
                       @Override
                       public void onChanged(List<NoteEntity> noteEntities) {
                       mNoteList.clear();
                       mNoteList.addAll(noteEntities);
                       if (noteAdaptor == null){
                           noteAdaptor = new NoteAdaptor(MainActivity.this,mNoteList);
                            recyclerView.setAdapter(noteAdaptor);
                           Toast.makeText(MainActivity.this,"Adaptor created",Toast.LENGTH_LONG).show();

                       }else {
                           Toast.makeText(MainActivity.this,"Adaptor not created",Toast.LENGTH_LONG).show();
                           noteAdaptor.notifyDataSetChanged();
                       }

                       }
                   };
            mViewModel = ViewModelProviders.of(this).get(ListActivityViewModel.class);
           mViewModel.mNotesList.observe(MainActivity.this,noteObserver);






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       switch (id){
           case R.id.add_sample_data:{
               addSampleData();
               return true;
           }

           case R.id.delete_all_data:{
               deleteAllData();
               return true;
           }
       }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllData() {
    mViewModel.deleteAllData();
       }

    private void addSampleData() {
           mViewModel.addSampleData();
    }
}
