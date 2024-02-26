package com.example.hfs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class approvedform extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String MobilePattern = "[0-9]{10}";
    TextInputLayout phone;
    TextView name,tphone,address,room,reason,filldate,lastdate,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvedform);
        phone=findViewById(R.id.phone);
        name=(TextView)findViewById(R.id.tv1);
        tphone=(TextView)findViewById(R.id.tv2);
        address=(TextView)findViewById(R.id.tv3);
        room=(TextView)findViewById(R.id.tv4);
        reason=(TextView)findViewById(R.id.tv5);
        filldate=(TextView)findViewById(R.id.tv6);
        lastdate=(TextView)findViewById(R.id.tv7);
        status=(TextView) findViewById(R.id.btn);
    }
    public void checkphonebtn(View v){
        if(!validatephone()){return;}
        System.out.println("onlick");
        String uphone = phone.getEditText().getText().toString().trim();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("leavingform");
        Query checkuser=reference.orderByChild("phone").equalTo(uphone);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    System.out.println("data found");
                    String Dname = dataSnapshot.child(uphone).child("name").getValue().toString().trim().toLowerCase();
                    String Dphone = dataSnapshot.child(uphone).child("phone").getValue().toString().trim();
                    String Daddress = dataSnapshot.child(uphone).child("address").getValue().toString().trim();
                    String Droom = dataSnapshot.child(uphone).child("room").getValue().toString().trim();
                    String Dreason = dataSnapshot.child(uphone).child("reason").getValue().toString().trim();
                    System.out.println(Dreason);
                    String Dfilldate = dataSnapshot.child(uphone).child("filleddate").getValue().toString().trim();
                    String Dlastdate = dataSnapshot.child(uphone).child("lastdate").getValue().toString().trim();
                    String Sstatus = dataSnapshot.child(uphone).child("status").getValue().toString().trim();
                    Intent intent=getIntent();
                    String uuname = intent.getStringExtra("uname").toLowerCase();
                    System.out.println(uuname);
                    if(Dname.equals(uuname)) {
                        name.append(Dname);
                        tphone.append(Dphone);
                        address.append(Daddress);
                        room.append(Droom);
                        reason.append(Dreason);
                        filldate.append(Dfilldate);
                        lastdate.append(Dlastdate);
                        if(Sstatus.equals("APPROVED")){
                        status.append(Sstatus);
                        status.setTextColor(Color.GREEN);
                                                        }
                        else{
                            status.setTextColor(Color.RED);
                            status.append(Sstatus);
                        }

                                            }
                }
                else {
                    notavailable();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void notavailable() {
        Toast.makeText(this,"FORM NOT AVAILABLE!!!...",Toast.LENGTH_LONG).show();
        finish();
    }

    public Boolean validatephone(){
        String nameval = phone.getEditText().getText().toString();
        if(nameval.isEmpty()){
            phone.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(MobilePattern)){
            phone.setError("Invalid Format");
            return false;
        }
        else {
            phone.setError(null);
            return true;
        }
    }
}