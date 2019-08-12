package com.example.notetakingapp2.Model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetakingapp2.EditorACtivity;
import com.example.notetakingapp2.R;
import com.example.notetakingapp2.Utils.Constants;
import com.example.notetakingapp2.database.NoteEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoteAdaptor extends RecyclerView.Adapter<NoteAdaptor.MyViewHolder>{
    @NonNull
    private Context context;
    private List<NoteEntity> mNoteEntity;


    public NoteAdaptor(@NonNull Context context, List<NoteEntity> mNoteEntity) {
        this.context = context;
        this.mNoteEntity = mNoteEntity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.note_list_layout,parent,false);

          return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final NoteEntity noteEntity = mNoteEntity.get(position);
        holder.textView.setText(noteEntity.getText());

        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditorACtivity.class);
                intent.putExtra(Constants.NOTE_ID_KEY,noteEntity.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteEntity.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        FloatingActionButton fab;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            textView=itemView.findViewById(R.id.note_text);
            fab = itemView.findViewById(R.id.fab_edit_note);

        }
    }
}
