package com.example.hfs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class emergencycontacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_emergencycontacts);
    }

    public void btn1(View v){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9876543210"));
        startActivity(i);
    }

    public void btn2(View v){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9876543210"));
        startActivity(i);
    }
    public void btn3(View v){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9876543210"));
        startActivity(i);
    }
    public void btn4(View v){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9876543210"));
        startActivity(i);
    }
    public void btn5(View v){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9876543210"));
        startActivity(i);
    }
    public void mbtn(View v){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9876543210"));
        startActivity(i);
    }

}