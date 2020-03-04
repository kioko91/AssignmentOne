package com.example.assignmentone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    EditText password;
    EditText username;
    //TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // final SharedPreferences preferences = getSharedPreferences( "preferences",MODE_PRIVATE);
        //final String textValue = preferences.getString("name","");
        //textView1.setText("Welcome "+textValue);

        button1 = (Button) findViewById(R.id.button1);
        password= findViewById(R.id.password);
        username= findViewById(R.id.username);
        //textView1= findViewById(R.id.textView1);




        if( username.getText().toString().length() == 0 )
            username.setError( "Username is required!" );
        if( password.getText().toString().length() <5)
            password.setError( "Password too short!" );

        SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
        int number = sharedPref.getInt("isLogged", 0);
        if(number == 0) {
            //Open the login activity and set this so that next it value is 1 then this conditin will be false.
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putInt("isLogged",1);
            prefEditor.commit();
        } else {
            startActivity(new Intent(MainActivity.this,HomePage.class));
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                String userString = password.getText().toString();
                //String nameString =username.getText().toString();

                if( password.getText().toString().length() <5){
                    password.setError( "Password too short!" );
                    Toast.makeText(MainActivity.this,"The password is too short!", Toast.LENGTH_SHORT).show();}
                else if (username.getText().toString().length() == 0){

                    username.setError( "Username is invalid!" );
                    Toast.makeText(MainActivity.this,"Username is invalid!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Success " ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);

                    //textView1.setText("Welcome " +username);
                }

                /*SharedPreferences.Editor editor= preferences.edit();
                editor.putString("name",nameString);
                editor.commit();*/

            }
        });
    }

}
