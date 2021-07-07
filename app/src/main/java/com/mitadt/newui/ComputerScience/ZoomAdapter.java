package com.mitadt.newui.ComputerScience;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mitadt.newui.R;

import java.util.ArrayList;

public class ZoomAdapter extends RecyclerView.Adapter<ZoomAdapter.myviewholder> {

    ArrayList<ZoomfileHolder> lecturelist;

    public ZoomAdapter(ArrayList<ZoomfileHolder> lecturelist) {
        this.lecturelist = lecturelist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_row_design,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.topic.setText(lecturelist.get(position).getTopic());
        holder.date.setText(lecturelist.get(position).getLectureDate());
        holder.time.setText(lecturelist.get(position).getLectureTime());
        holder.joinzoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(lecturelist.get(position).getMeetingLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                holder.joinzoom.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lecturelist.size();
    }


    public class myviewholder extends RecyclerView.ViewHolder{

        TextView topic,date,time;
        Button joinzoom;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.zoomDate);
            time = itemView.findViewById(R.id.zoomTime);
            topic = itemView.findViewById(R.id.zoomTopic);
            joinzoom = itemView.findViewById(R.id.zoomJoin);
        }
    }

}
