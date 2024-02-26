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
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.DelayQueue;

public class leavingform extends AppCompatActivity {
    TextInputLayout xname, xroom, xphone, xaddress, xreason;
    DatePicker dp;
    FirebaseDatabase rootNode;
    String MobilePattern = "[0-9]{10}";
    String roompattern = "[0-9]{3}";
    String namepattern = "[a-zA-Z ]+";

    DatabaseReference reference;
    TextView xstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("S.R.E.S");

        setContentView(R.layout.activity_leavingform);

        xname = findViewById(R.id.fname);
        xroom = findViewById(R.id.room);
        xphone = findViewById(R.id.phone);
        xaddress = findViewById(R.id.address);
        xreason = findViewById(R.id.reason);
        dp = (DatePicker) findViewById(R.id.date);
        xstatus = (TextView) findViewById(R.id.status);


    }

    public void submit(View v) {

        if (!isConnected(leavingform.this)) {
            showcustomalert();
        }

        if (!validatename() | !validateaddress() | !validatereason() | !validatephone() | !validateroom()) {
            return;
        }


        String name = xname.getEditText().getText().toString().trim();
        String phone = xphone.getEditText().getText().toString().trim();
        String address = xaddress.getEditText().getText().toString().trim();
        String room = xroom.getEditText().getText().toString().trim();
        String reason = xreason.getEditText().getText().toString().trim();
        String status = xstatus.getText().toString().trim();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dp.getDayOfMonth()).append("-").append(dp.getMonth() + 1).append("-").append(dp.getYear());
        String lastdate = stringBuilder.toString().trim();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        String filleddate=formattedDate.toString().trim();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("leavingform");

        Leavinguserhelperclass userhelperclass = new Leavinguserhelperclass(name, phone, address, room, reason,filleddate, lastdate, status);
        reference.child(phone).setValue(userhelperclass);
        done();


    }




    private boolean isConnected(leavingform leavingform) {

        ConnectivityManager connectivityManager = (ConnectivityManager) leavingform.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileconn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wificonn != null && wificonn.isConnected()) || (mobileconn != null && mobileconn.isConnected())) {
            return true;
        } else {
            return false;
        }


    }


    private void showcustomalert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(leavingform.this);
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
                        startActivity(new Intent(getApplicationContext(), leavingform.class));
                        finish();
                    }
                }).show();


    }

    public Boolean validatename() {
        Intent intent=getIntent();
        String uuname = intent.getStringExtra("uname");
        String nameval = xname.getEditText().getText().toString();
        if (nameval.isEmpty()) {
            xname.setError("Field cannot be empty");
            return false;
        } else if (!nameval.matches(namepattern)) {
            xname.setError("Invalid Format");
            return false;
        } else if (!nameval.toLowerCase().matches(uuname.toLowerCase())){
            xname.setError("Invalid User !!");
            return false;
        }
         else {
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
            xroom.setError("Invalid Format");
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
    private boolean validateaddress() {
        String nameval = xaddress.getEditText().getText().toString();
        if (nameval.isEmpty()) {
            xaddress.setError("Field cannot be empty");
            return false;

        } else {
            xaddress.setError(null);
            return true;
        }
    }
    private boolean validatereason() {
        String nameval = xreason.getEditText().getText().toString();
        if (nameval.isEmpty()) {
            xreason.setError("Field cannot be empty");
            return false;

        } else {
            xreason.setError(null);
            return true;
        }

    }
    public void done(){
        Toast.makeText(this,"Form filled Successfully.......please login to see form status",Toast.LENGTH_LONG).show();
      finish();
    }


}




