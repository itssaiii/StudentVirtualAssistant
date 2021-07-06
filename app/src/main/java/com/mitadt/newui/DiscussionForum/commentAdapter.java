package com.mitadt.newui.DiscussionForum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mitadt.newui.R;

import java.util.ArrayList;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.myviewholder> {


    ArrayList<commentfileholder> answerlist;

    public commentAdapter(ArrayList<commentfileholder> answerlist) {
        this.answerlist = answerlist;
    }




    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment_design,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {


        holder.cname.setText(answerlist.get(position).getCname());
        holder.creply.setText(answerlist.get(position).getComment());
        holder.cques.setText(answerlist.get(position).getCques());
        holder.ctime.setText(answerlist.get(position).getCtime());
        holder.cdate.setText(answerlist.get(position).getCdate());


    }

    @Override
    public int getItemCount() {
        return answerlist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView cdate,ctime,cname,cques,creply;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            cdate = itemView.findViewById(R.id.dateviewanswer);
            ctime = itemView.findViewById(R.id.timeviewanswer);
            cname = itemView.findViewById(R.id.fullnameviewanswer);
            cques = itemView.findViewById(R.id.questionofcomment);
            creply = itemView.findViewById(R.id.replyofcomment);
        }
    }


}



