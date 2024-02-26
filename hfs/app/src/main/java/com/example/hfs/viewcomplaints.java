package com.example.hfs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class viewcomplaints extends AppCompatActivity {
    RecyclerView recyclerView;
    myfirebaseadapter2 myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcomplaints);
        getSupportActionBar().setTitle("S.R.E.S");
        recyclerView=(RecyclerView)findViewById(R.id.recview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Complaintuserhelperclass> options =
                new FirebaseRecyclerOptions.Builder<Complaintuserhelperclass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("complaint"), Complaintuserhelperclass.class)
                        .build();
        myadapter= new myfirebaseadapter2(options);
        recyclerView.setAdapter(myadapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        myadapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myadapter.stopListening();
    }
}