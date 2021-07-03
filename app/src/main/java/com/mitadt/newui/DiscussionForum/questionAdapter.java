package com.mitadt.newui.DiscussionForum;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mitadt.newui.ComputerScience.myadapter;
import com.mitadt.newui.MainPage;
import com.mitadt.newui.R;

import java.util.ArrayList;

public class questionAdapter extends RecyclerView.Adapter<questionAdapter.myviewholder> {



    ArrayList<questionFileholder> datalist;

    public questionAdapter(ArrayList<questionFileholder> datalist) {
        this.datalist = datalist;
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question_design,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {



        holder.date.setText(datalist.get(position).getDate());
        holder.time.setText(datalist.get(position).getTime());
        holder.name.setText(datalist.get(position).getName());
        holder.question.setText(datalist.get(position).getDescription());
        holder.likes.setText(String.valueOf(datalist.get(position).getLikes()));
        holder.comments.setText(String.valueOf(datalist.get(position).getComments()));

        holder.ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.ans.getContext(), ViewComments.class);
                intent.putExtra("title",datalist.get(position).getTitle());
                intent.putExtra("description",datalist.get(position).getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.ans.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        ImageView profilepic,ans;
        TextView date,time,name,likes,comments,question;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            profilepic = itemView.findViewById(R.id.questionprofileview);
            date = itemView.findViewById(R.id.dateviewquestion);
            time = itemView.findViewById(R.id.timeviewquestion);
            name = itemView.findViewById(R.id.fullnameviewquestion);
            likes = itemView.findViewById(R.id.likesnumber);
            comments = itemView.findViewById(R.id.commentsnumber);
            question = itemView.findViewById(R.id.questionview);

            ans = itemView.findViewById(R.id.commentbutton);


        }
    }

}
