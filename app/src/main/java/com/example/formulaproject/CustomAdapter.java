package com.example.formulaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaproject.RaceItem;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    private ArrayList<RaceItem> races;
    public CustomAdapter(ArrayList<RaceItem> races){
        this.races=races;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.races,parent,false);
        final MyViewHolder myViewHolder=new MyViewHolder(view);
        myViewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),String.valueOf(myViewHolder.location.getText().toString()), Toast.LENGTH_SHORT).show();
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView location=holder.location;
        TextView time=holder.time;
        TextView date=holder.date;
        ImageView track=holder.track;
        location.setText(races.get(position).getLocation());
        time.setText(races.get(position).getTime());
        date.setText(races.get(position).getDate());
        track.setImageResource(races.get(position).getImageId());

    }

    @Override
    public int getItemCount() {
        return races.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView location,time,date;
        LinearLayout item;
        ImageView track;
        public MyViewHolder(View itemView){
            super(itemView);
            location=(TextView)itemView.findViewById(R.id.Location);
            time=(TextView)itemView.findViewById(R.id.Time);
            date=(TextView)itemView.findViewById(R.id.Date);
            track=(ImageView)itemView.findViewById(R.id.Track);
            item=(LinearLayout)itemView.findViewById(R.id.race_item);

        }
    }


}
