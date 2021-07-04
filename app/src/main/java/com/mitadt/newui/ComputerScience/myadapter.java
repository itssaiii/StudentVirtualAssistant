package com.mitadt.newui.ComputerScience;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mitadt.newui.R;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {

    ArrayList<model> datalist;

    public myadapter(ArrayList<model> datalist) {
        this.datalist = datalist;
    }


    @NonNull
    @Override
    public myadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myadapter.myviewholder holder, int position) {
        holder.t1.setText(datalist.get(position).getFilename());
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.img1.getContext(),OpenAdsaPdf.class);
                intent.putExtra("filename",datalist.get(position).getFilename());
                intent.putExtra("fileurl",datalist.get(position).getFileurl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.img1.getContext().startActivity(intent);

            }
        });

        holder.pune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView t1;
        ImageView img1;

        CardView pune;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.viewpdfimage);
            t1=itemView.findViewById(R.id.viewpdfname);
            pune = itemView.findViewById(R.id.pdfview);

        }
    }
}
