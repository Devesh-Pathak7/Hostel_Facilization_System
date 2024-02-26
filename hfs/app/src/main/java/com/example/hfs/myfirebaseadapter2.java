package com.example.hfs;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.collection.LLRBNode;

public class myfirebaseadapter2 extends FirebaseRecyclerAdapter<Complaintuserhelperclass,myfirebaseadapter2.myviewholder> {
    public myfirebaseadapter2(@NonNull FirebaseRecyclerOptions<Complaintuserhelperclass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Complaintuserhelperclass model) {

        holder.name.append(model.getName());
        holder.phone.append(model.getPhone());
        holder.address.append(model.getHostelno());
        holder.room.append(model.getRoom());
        holder.reason.append(model.getComplaint());
        holder.filleddate.append(model.getFilleddate());
           }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complainsinglerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name, phone, address, room, reason,filleddate,lastdate;
        Button approve;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.tv1);
            phone=(TextView)itemView.findViewById(R.id.tv2);
            address=(TextView)itemView.findViewById(R.id.tv3);
            room=(TextView)itemView.findViewById(R.id.tv4);
            reason=(TextView)itemView.findViewById(R.id.tv5);
            filleddate=(TextView)itemView.findViewById(R.id.tv6);
            lastdate=(TextView)itemView.findViewById(R.id.tv7);
            approve=(Button) itemView.findViewById(R.id.btn);

        }
    }
}
