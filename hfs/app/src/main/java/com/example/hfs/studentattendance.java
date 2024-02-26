package com.example.hfs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslCertificate;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class studentattendance extends AppCompatActivity {
Button b;
TextInputLayout xname,xroom,xphone,xhostelno;
FirebaseDatabase rootNode;
DatabaseReference reference;
String Droom;
    String MobilePattern = "[0-9]{10}";
    String roompattern = "[0-9]{3}";
    String hostelnopattern = "[A-Z0-9]{2}";
    String namepattern = "[a-zA-Z ]+";
    RadioButton rb1,rb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentattendance);
        b=(Button)findViewById(R.id.submit);
        xname = findViewById(R.id.name);
        xroom = findViewById(R.id.room);
        xphone = findViewById(R.id.phone);
        xhostelno = findViewById(R.id.hostelno);

       rb1=(RadioButton)findViewById(R.id.rb1);
       rb2=(RadioButton)findViewById(R.id.rb2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(studentattendance.this)) {
                    showcustomalert();
                }

                if (!validatename() | !validatehostelno() |  !validatephone() | !validateroom()) {
                    return;
                }
                if(!validuser()){
                    return;
                }


                String name = xname.getEditText().getText().toString().trim();
                String phone = xphone.getEditText().getText().toString().trim();
                String hostelno = xhostelno.getEditText().getText().toString().trim();
                String room=xroom.getEditText().getText().toString().trim();
                String present_status="hi";
                if(rb1.isChecked()){
                    present_status="Present";
                }
                else if(rb2.isChecked()){
                    present_status="Absent";
                }




                Date c = Calendar.getInstance().getTime();


                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                String filleddate=formattedDate.toString().trim();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                // User does not exist. NOW call createUserWithEmailAndPassword


                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("attendance");


                Attendenceuserhelperclass userhelperclass = new Attendenceuserhelperclass(name, phone, hostelno, room, present_status,filleddate);
                reference.child(room).setValue(userhelperclass);

                done();


            }
        });
    }





    private boolean validuser() {
        Intent intent=getIntent();
        String uuname = intent.getStringExtra("uname");
        if(uuname.equals(xname.getEditText().getText().toString())){
         return true;
        }
        else{
            xname.setError("INVALID USER NAME FOUND (CASE SENSITIVE)");
            return false;
        }
    }


    private boolean isConnected(studentattendance studentattendance) {

        ConnectivityManager connectivityManager = (ConnectivityManager) studentattendance.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileconn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wificonn != null && wificonn.isConnected()) || (mobileconn != null && mobileconn.isConnected())) {
            return true;
        } else {

            return false;
        }

    }


    private void showcustomalert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(studentattendance.this);
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
                        startActivity(new Intent(getApplicationContext(), studentattendance.class));
                        finish();
                    }
                }).show();


    }

    public Boolean validatename() {
        String nameval = xname.getEditText().getText().toString();
        if (nameval.isEmpty()) {
            xname.setError("Field cannot be empty");
            return false;
        } else if (!nameval.matches(namepattern)) {
            xname.setError("Invalid Format");
            return false;
        } else {
            xname.setError(null);
            return true;
        }


    }
    public Boolean validateroom(){
        String nameval = xroom.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xroom.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(roompattern)){
            xroom.setError("Invalid Format (use Capital Alphabets)");
            return false;
        }
        else {
            xroom.setError(null);
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
    private boolean validatehostelno() {
        String nameval = xhostelno.getEditText().getText().toString();
        if (nameval.isEmpty()) {
            xhostelno.setError("Field cannot be empty");
            return false;

        }  else if(!nameval.matches(hostelnopattern)){
            xhostelno.setError("Invalid Format");
            return false;
        }
        else {
            xhostelno.setError(null);
            return true;
        }
    }


    public void done(){

        Toast.makeText(this,"Attendance filled Successfully.......",Toast.LENGTH_LONG).show();
        Intent i=new Intent(studentattendance.this,studentlogin.class);
        startActivity(i);

    }

}