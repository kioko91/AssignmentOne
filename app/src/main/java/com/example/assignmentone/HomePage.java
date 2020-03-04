package com.example.assignmentone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomePage extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    private TextView textView1;
    private TextView textView3;
   private EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final SharedPreferences preferences = getSharedPreferences( "preferences",MODE_PRIVATE);
        final String textValue = preferences.getString("name","");


        imageView= findViewById(R.id.imageView);
        button=findViewById(R.id.button1);
        textView3=findViewById(R.id.textView3);
        textView1=findViewById(R.id.textView1);
        username=findViewById(R.id.username);


        textView1.setText("Welcome "+textValue);

       /* String nameString =username.getText().toString();
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("name",nameString);
        editor.commit();*/

        //textView1.setText("Welcome " +nameString);



        String date_n =new SimpleDateFormat("MMM dd,yyyy", Locale.getDefault()).format(new Date());
        textView3.setText(date_n);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View f) {
                imageView.animate().rotation(360).setDuration(2000);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                Intent intent = new Intent(HomePage.this,OrmActivity.class);
                startActivity(intent);
            }
        });
    }
}
