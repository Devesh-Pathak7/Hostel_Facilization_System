package com.example.hfs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class viewnotice extends AppCompatActivity {
    RecyclerView recyclerView;
    myfirebaseadapter3 myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnotice);
        getSupportActionBar().setTitle("S.R.E.S");
        recyclerView=(RecyclerView)findViewById(R.id.recview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<noticeuserhelperclass> options =
                new FirebaseRecyclerOptions.Builder<noticeuserhelperclass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("notice"), noticeuserhelperclass.class)
                        .build();
        myadapter= new myfirebaseadapter3(options);
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