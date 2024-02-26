package com.example.hfs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Teachersportal extends AppCompatActivity {
    TextView user;
    public String userr=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getSupportActionBar().setTitle("S.R.E.S");
        setContentView(R.layout.activity_teachersportal);


        user=(TextView)findViewById(R.id.user);
        Intent intent = getIntent();
        String str = intent.getStringExtra("name");
        user.append(str);

        System.out.println(str);


    }
    public void view(View v){
        Intent i =new Intent(Teachersportal.this,viewLeavingForm.class);
        startActivity(i);
    }
    public void checkcomp(View v){
        Intent i =new Intent(Teachersportal.this,viewcomplaints.class);
        startActivity(i);
    }
    public void anotice(View v){
        Intent i =new Intent(Teachersportal.this,addnotice.class);
        startActivity(i);
    }
    public void checkattendance(View v){

        Intent i =new Intent(Teachersportal.this,viewattendance.class);
        startActivity(i);


    }
}
