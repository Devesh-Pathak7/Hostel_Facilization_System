package com.example.hfs;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class messstart extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_messstart);
    }

    public void mess1(View v){
        Intent i= new Intent(messstart.this,mess.class);
        startActivity(i);
    }
    public void mess2(View v){
        Intent i= new Intent(messstart.this,mess2.class);
        startActivity(i);
    }
    public void mess3(View v){
        Intent i= new Intent(messstart.this,mess3.class);
        startActivity(i);
    }
    public void mess4(View v){
        Intent i= new Intent(messstart.this,mess4.class);
        startActivity(i);
    }

}