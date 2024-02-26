package com.example.hfs;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class studentsignup extends AppCompatActivity {
    TextInputLayout xname,xroom,xemail,xphone,xpassword;
Button go,loginbutton;
FirebaseDatabase rootNode;
DatabaseReference reference;
String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
String MobilePattern = "[0-9]{10}";
String roompattern = "[0-9]{3}";
String namepattern = "[a-zA-Z ]+";
final String status="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("S.R.E.S");


        if(! isConnected(studentsignup.this)){
            showcustomalert();
        }
        setContentView(R.layout.activity_studentsignup);

        xname=findViewById(R.id.name);
        xroom=findViewById(R.id.room);
        xemail=findViewById(R.id.email);
        xphone=findViewById(R.id.phone);
        xpassword=findViewById(R.id.password);
        go=(Button)findViewById(R.id.go);
        loginbutton=(Button)findViewById(R.id.loginbutton);

    }




    private boolean isConnected(studentsignup studentsignup) {

        ConnectivityManager connectivityManager= (ConnectivityManager) studentsignup.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileconn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((wificonn !=null && wificonn.isConnected()) || (mobileconn !=null && mobileconn.isConnected()))
        {
            return true;
        }
        else {

            return false;
        }


    }


    private void showcustomalert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(studentsignup.this);
        builder.setMessage("Please connect to a network")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(),studentsignup.class));
                        finish();
                    }
                }).show();


    }


    public Boolean validatename(){
       String nameval = xname.getEditText().getText().toString();
       if(nameval.isEmpty()){
           xname.setError("Field cannot be empty");
           return false;
       } else if(!nameval.matches(namepattern)){
           xname.setError("Invalid Format");
           return false;
       }
       else {
           xname.setError(null);
           return true;
       }

    }
    public Boolean validateemail(){
        String nameval = xemail.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xemail.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(emailPattern)){
            xemail.setError("Invalid Format");
            return false;
        }
        else {
            xemail.setError(null);
            return true;
        }

    }
    public Boolean validatephone(){
        String nameval = xphone.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xphone.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(MobilePattern)){
            xphone.setError("Invalid Format");
            return false;
        }
        else {
            xphone.setError(null);
            return true;
        }

    }
    public Boolean validateroom(){
        String nameval = xroom.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xroom.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(roompattern)){
            xroom.setError("Invalid Format");
            return false;
        }
        else {
            xroom.setError(null);
            return true;
        }

    }
    public Boolean validatepassword(){
        String nameval = xpassword.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xpassword.setError("Field cannot be empty");
            return false;
        } else if(nameval.length() < 6){
            xpassword.setError("should be greater than 6 digits");
            return false;
        }
        else {
            xpassword.setError(null);
            return true;
        }

    }




    public void go(View v){
        if(! isConnected(studentsignup.this)){
            showcustomalert();
        }

        if(!validatename() | !validateemail() | !validatepassword() | !validatephone() | !validateroom()){ return; }


            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.child("studentsign").child(xphone.getEditText().getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        phoneExist();

                    } else {


                        // User does not exist. NOW call createUserWithEmailAndPassword


                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("studentsign");


                        String name, password, room, phone, email;
                        name = xname.getEditText().getText().toString();
                        password = xpassword.getEditText().getText().toString();
                        room = xroom.getEditText().getText().toString();
                        phone = xphone.getEditText().getText().toString();
                        email = xemail.getEditText().getText().toString();


                        Userhelperclass userhelperclass = new Userhelperclass(name, email, phone, password, room, status);
                        reference.child(phone).setValue(userhelperclass);

                        done();

                        // Your previous code here.

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    private void phoneExist() {
        Toast.makeText(this,"PHONE NUMBER ALREADY PRESENT !!",Toast.LENGTH_LONG).show();
        xphone.setError("Number Already Present");
        xphone.getEditText().setText("");
        xname.getEditText().setText("");
        xroom.getEditText().setText("");
        xpassword.getEditText().setText("");
        xemail.getEditText().setText("");

    }

    public void done(){
        Toast.makeText(this,"Registration Done Successfully",Toast.LENGTH_LONG).show();
    Intent i=new Intent(studentsignup.this,studentlogin.class);
    startActivity(i);
}

    public void back(View v){
        Intent i=new Intent(studentsignup.this,studentlogin.class);
        startActivity(i);
    }

}