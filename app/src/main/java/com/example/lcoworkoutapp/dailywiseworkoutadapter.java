package com.example.lcoworkoutapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dailywiseworkoutadapter extends RecyclerView.Adapter<dailywiseworkoutadapter.holder> {

    Context context;
    int dayworkouts[];
    String names[];
    int images[];
    String description[];
    LayoutInflater layoutInflater;

    public dailywiseworkoutadapter(Context context, int[] dayworkouts, String[] names, int[] images, int[] duration,String description[]) {
        this.context = context;
        this.dayworkouts = dayworkouts;
        this.names = names;
        this.images = images;
        this.duration = duration;
        this.description=description;
        layoutInflater=LayoutInflater.from(context);
    }

    int duration[];
    class holder extends RecyclerView.ViewHolder {
   ImageView imageView;
   TextView textView;
   TextView textView1;
   TextView textView2;
        public holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.daily_image_view);
            textView=itemView.findViewById(R.id.daily_workout_name);
            textView1=itemView.findViewById(R.id.daily_workout_duration);
            textView2=itemView.findViewById(R.id.description);
        }
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=layoutInflater.inflate(R.layout.dailyworkoutview,parent,false);

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
       holder.imageView.setImageResource(images[dayworkouts[position]]);
       holder.textView.setText(names[dayworkouts[position]]);
       holder.textView1.setText(duration[dayworkouts[position]]/1000+":sec");
       holder.textView2.setText(description[dayworkouts[position]]);


    }

    @Override
    public int getItemCount() {
        return 5;
    }


}