package com.example.lcoworkoutapp;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class workoutrecycleviewAdapter extends RecyclerView.Adapter<workoutrecycleviewAdapter.recycleViewHolder> {
    LayoutInflater layoutInflater;
    int randomtext;
    int randomText2 = 0;
    Random random = new Random();
    String names[];
    int images[];
    int duration[];
    Context context;
    int seconds;
    CountDownTimer countDownTimer;
    ArrayList<Integer> list = new ArrayList<>();

    public workoutrecycleviewAdapter(Context context, String names[], int images[], int duration[]) {
        this.names = names;
        this.images = images;
        this.duration = duration;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    class recycleViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        TextView textView1;

        public recycleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.random_image_view);
            textView = itemView.findViewById(R.id.random_workout_name);
            textView1 = itemView.findViewById(R.id.random_workout_duration);
        }
    }

    @NonNull
    @Override
    public recycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.workoutview, parent, false);

        return new recycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleViewHolder holder, int position) {

        randomtext = random.nextInt(13);
        seconds = duration[randomtext] / 1000;

        countDownTimer = new CountDownTimer(duration[randomtext], 1000) {
            @Override
            public void onTick(long l) {
                seconds = seconds - 1;
                Toast.makeText(context, "00:" + seconds, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Toast.makeText(context, "finished", Toast.LENGTH_SHORT).show();
                seconds = 0;
            }
        };
//        countDownTimer.start();

//        Toast.makeText(context, System.currentTimeMillis() + " ", Toast.LENGTH_SHORT).show();


        holder.imageView.setImageResource(images[randomtext]);
        holder.textView.setText(names[randomtext]);
        holder.textView1.setText((duration[randomtext] / 1000) + " sec");
        list.add(randomtext);

    }

    @Override
    public int getItemCount() {
        return 5;
    }


}
