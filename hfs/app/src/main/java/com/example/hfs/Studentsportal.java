package com.example.hfs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Studentsportal extends AppCompatActivity {
TextView user;
public String userr=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_studentsportal);

        user=(TextView)findViewById(R.id.user);
        Intent intent = getIntent();
        String str = intent.getStringExtra("name");
        user.append(str);
    }
    public void fill(View v){
        user=(TextView)findViewById(R.id.user);
        Intent intent = getIntent();
        String str = intent.getStringExtra("name");

        Intent in =new Intent(Studentsportal.this,leavingform.class);
        in.putExtra("uname",str);
        startActivity(in);
    }

    public void complaint(View view) {
        Intent i =new Intent(Studentsportal.this,studentcomplaint.class);
        startActivity(i);
    }
    public void emergency(View v){
        Intent i =new Intent(Studentsportal.this,emergencycontacts.class);
        startActivity(i);
    }
    public void messinfo(View v){
        Intent i =new Intent(Studentsportal.this,messstart.class);
        startActivity(i);
    }
    public void attendence(View v){
        user=(TextView)findViewById(R.id.user);
        Intent intent = getIntent();
        String str = intent.getStringExtra("name");

        Intent in =new Intent(Studentsportal.this,studentattendance.class);
        in.putExtra("uname",str);
        startActivity(in);
    }
    public void viewnotices(View v){
        Intent i =new Intent(Studentsportal.this,viewnotice.class);
        startActivity(i);
    }
    public void checkstatus(View v){
        user=(TextView)findViewById(R.id.user);
        Intent intent = getIntent();
        String str = intent.getStringExtra("name");

        Intent in =new Intent(Studentsportal.this,approvedform.class);
        in.putExtra("uname",str);
        startActivity(in);
    }
}