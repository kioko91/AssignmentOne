package com.example.assignmentone;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reactiveandroid.ReActiveAndroid;
import com.reactiveandroid.ReActiveConfig;
import com.reactiveandroid.internal.database.DatabaseConfig;
import com.reactiveandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrmActivity extends AppCompatActivity {


RecyclerView notesList;
FloatingActionButton add;

List<Note> notes;
NoteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orm);
        DatabaseConfig appDatabase =
                new DatabaseConfig.Builder(AppDatabase.class)
                        .addModelClasses(Note.class)
                .build();

        ReActiveAndroid.init
                (new ReActiveConfig.Builder(this)
                .addDatabaseConfigs(appDatabase)
                .build());


        notesList=findViewById(R.id.notes_list);
        add=findViewById(R.id.add);

        notes = Select.from(Note.class).fetch();
        notesList.setLayoutManager(new LinearLayoutManager(OrmActivity.this));
        adapter = new NoteAdapter(notes);
        notesList.setAdapter(adapter);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(OrmActivity.this);
                dialog.setContentView(R.layout.new_note);
                final String date_n =new SimpleDateFormat("MMM dd,yyyy", Locale.getDefault()).format(new Date());
                final EditText title = dialog.findViewById(R.id.title);
                final EditText note = dialog.findViewById(R.id.note);
                Button save = dialog.findViewById(R.id.save_note);


                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String titleString= title.getText().toString();
                        String noteString =note.getText().toString();

                        Note note = new Note(date_n, titleString,noteString);
                        note.save();
                        startActivity(new Intent(OrmActivity.this,OrmActivity.class));
                    }
                });

dialog.show();

            }
        });
    }
}
