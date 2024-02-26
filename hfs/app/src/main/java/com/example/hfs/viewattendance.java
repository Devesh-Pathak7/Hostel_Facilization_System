package com.example.hfs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

public class viewattendance extends AppCompatActivity {
TextView name,phone,hostel,date,roomno,status;
TextInputLayout room;
    FirebaseDatabase rootNode;

    String roompattern = "[0-9]{3}";
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewattendance);

        name=(TextView)findViewById(R.id.name);
        phone=(TextView)findViewById(R.id.phone);
        hostel=(TextView)findViewById(R.id.hostel);
        date=(TextView)findViewById(R.id.date);
        roomno=(TextView)findViewById(R.id.roomno);
        status=(TextView)findViewById(R.id.status);
        room=findViewById(R.id.room);


    }
    public void checkresult(View v){
        String roomnocheck=room.getEditText().getText().toString().trim();
        if(!validateroom()){ return; }

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("attendance");
        Query checkuser=reference.orderByChild("room").equalTo(roomnocheck);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String Dphone = dataSnapshot.child(roomnocheck).child("phone").getValue().toString().trim();
                    String Dname = dataSnapshot.child(roomnocheck).child("name").getValue().toString().trim();
                    String Dhostel = dataSnapshot.child(roomnocheck).child("hostelno").getValue().toString().trim();
                    String Droom = dataSnapshot.child(roomnocheck).child("room").getValue().toString().trim();
                    String Dfilleddate = dataSnapshot.child(roomnocheck).child("filleddate").getValue().toString().trim();
                    String present_status=dataSnapshot.child(roomnocheck).child("present_status").getValue().toString().trim();
                    Date c = Calendar.getInstance().getTime();


                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    String formattedDate = df.format(c);
                    String todaydate=formattedDate.toString().trim();

                    if(Dfilleddate.matches(todaydate)){
                        name.setText("Name : "+Dname);
                        hostel.setText("Hostel No. : "+Dhostel);
                        phone.setText("Phone : "+Dphone);
                        roomno.setText("Room : "+Droom);
                        status.setText("Present Status : "+present_status);
                        date.setText("Date : "+Dfilleddate);

                    }
                    else { notfilled(); }


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

    private void notfilled() {
        Toast.makeText(this,"Attendance not filled ",Toast.LENGTH_LONG).show();

        name.setText(" ");
        hostel.setText(" ");
        phone.setText(" ");
        roomno.setText(" ");
        status.setText(" ");
        date.setText(" ");

    }

    public void usererror(){
    Toast.makeText(this,"Room Number Not Available",Toast.LENGTH_LONG).show();
        name.setText(" ");
        hostel.setText(" ");
        phone.setText(" ");
        roomno.setText(" ");
        status.setText(" ");
        date.setText(" ");
}
    private boolean validateroom() {
        String nameval = room.getEditText().getText().toString();
        if(nameval.isEmpty()){
            room.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(roompattern)){
            room.setError("Invalid Format");
            return false;
        }
        else {
            room.setError(null);
            return true;
        }
    }
}