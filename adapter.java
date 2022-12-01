package com.example.list_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.RealmResults;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    Context context;
    RealmResults<firstnotepage>notelist;

    public adapter(Context context, RealmResults<firstnotepage> notelist) {
        this.context = context;
        this.notelist = notelist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        firstnotepage notepage=notelist.get(position);
        holder.descriptionoutput.setText(notepage.getDescription());
        String formatedTime = DateFormat.getDateTimeInstance().format(notepage.createdtime);

        holder.timeoutput.setText(formatedTime);


    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView descriptionoutput;
        TextView timeoutput;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionoutput=itemView.findViewById(R.id.wordoutput);
            timeoutput=itemView.findViewById(R.id.timeout);

        }











    }
}
