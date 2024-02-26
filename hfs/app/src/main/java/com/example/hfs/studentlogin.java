package com.example.hfs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;

public class    studentlogin extends AppCompatActivity {
TextInputLayout xphone,xpassword;
CheckBox remember;
Button goo;
String MobilePattern = "[0-9]{10}";
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(! isConnected(studentlogin.this)){
            showcustomalert();
        }
        getSupportActionBar().setTitle("S.R.E.S");

        setContentView(R.layout.activity_studentlogin);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String shareduser = preferences.getString("user", "");
        String sharedpass = preferences.getString("password", "");



        xpassword=findViewById(R.id.password);
        xphone=findViewById(R.id.phone);
        remember=(CheckBox)findViewById(R.id.remember);
        goo=(Button)findViewById(R.id.submit);

        xphone.getEditText().setText(shareduser);
        xpassword.getEditText().setText(sharedpass);

        goo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String upassword=xpassword.getEditText().getText().toString();
                String uphone=xphone.getEditText().getText().toString();



                if(! isConnected(studentlogin.this)){
                    showcustomalert();
                }
                if(!validatephone() | !validatepassword()){ return; }

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("studentsign");
                Query checkuser=reference.orderByChild("phone").equalTo(uphone);
                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                            String Dpassword = dataSnapshot.child(uphone).child("password").getValue().toString().trim();
                            String Dname = dataSnapshot.child(uphone).child("name").getValue().toString().trim();

                            String Dstatus = dataSnapshot.child(uphone).child("status").getValue().toString().trim();

                            if(Dpassword.equals(upassword)){

                                if(remember.isChecked()){
                                    session();
                                }else { removesession();}
                                if(Dstatus.equals("0")){

                                    xpassword.getEditText().setText("");
                                Intent i = new Intent(studentlogin.this,Studentsportal.class);
                                i.putExtra("name",Dname);
                                startActivity(i);}
                                else if(Dstatus.equals("1")){
                                    Intent i = new Intent(studentlogin.this,Teachersportal.class);
                                    i.putExtra("name",Dname);
                                    startActivity(i);
                                }

                            }
                            else {
                                passworderror();
                            }



                        }
                        else {
                            usererror();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }




        });
    }

    private void removesession() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(studentlogin.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user","");
        editor.putString("password","");
        editor.apply();
    }

    private void session() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(studentlogin.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user",xphone.getEditText().getText().toString().trim());
        editor.putString("password",xpassword.getEditText().getText().toString().trim());
        editor.apply();

    }

    private boolean isConnected(studentlogin studentlogin) {

            ConnectivityManager connectivityManager= (ConnectivityManager) studentlogin.getSystemService(Context.CONNECTIVITY_SERVICE);
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
        AlertDialog.Builder builder=new AlertDialog.Builder(studentlogin.this);
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
                       startActivity(new Intent(getApplicationContext(),studentlogin.class));
                        finish();
                    }
                }).show();


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

public void usererror(){
    Toast.makeText(this,"Please check the phone number",Toast.LENGTH_LONG).show();
    xphone.getEditText().setText("");
    xphone.setError("Please enter correct phone number");
    xpassword.getEditText().setText("");
}

public void passworderror(){
    Toast.makeText(this,"Please check the password",Toast.LENGTH_LONG).show();
    xpassword.getEditText().setText("");
    xpassword.setError("Please enter correct Password");
}
    public void next(View v){
        Intent i=new Intent(studentlogin.this,studentsignup.class);
        startActivity(i);
    }
}