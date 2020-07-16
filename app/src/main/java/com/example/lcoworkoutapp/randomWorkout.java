package com.example.lcoworkoutapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class randomWorkout extends AppCompatActivity {

    EditText no_of_sets;
    RecyclerView recyclerView;
    Button button;
    ArrayList<Integer> listofrandom = new ArrayList<>();
    workoutrecycleviewAdapter workoutrecycleviewAdapter;
    static String workoutnamesarray[] = {"plank", "crunches", "straight knee crunches", "bends", "bends", "dumbbell rows", "dumbbell curls", "knee exercise", "yoga", "chest pull", "incline bench press", "bench press", "shoulder flies", "crunches"};
    static int workoutimages[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
            R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n};
    static int duration[] = {45000, 30000, 30000, 45000, 30000, 45000, 30000, 30000, 45000, 30000, 30000, 30000, 45000, 30000};

    ListView random_workout_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_workout);

        recyclerView = findViewById(R.id.randomrecyclerview);
        workoutrecycleviewAdapter = new workoutrecycleviewAdapter(this, workoutnamesarray, workoutimages, duration);
        recyclerView.setAdapter(workoutrecycleviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(randomWorkout.this));
        button = findViewById(R.id.button1);
        no_of_sets = findViewById(R.id.no_sets);
        listofrandom = workoutrecycleviewAdapter.list;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (no_of_sets.getText().toString().isEmpty()) {
                    Toast.makeText(randomWorkout.this, "Please enter the number of sets", Toast.LENGTH_SHORT).show();
                } else {

                    if(Integer.parseInt(no_of_sets.getText().toString())>10)
                    {
                        Toast.makeText(randomWorkout.this, "Please maintain the number of set below 10", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(randomWorkout.this, randomworkoutreal.class);
                        intent.putIntegerArrayListExtra("list_of_random", listofrandom);
                        intent.putExtra("sets", no_of_sets.getText().toString());
                        startActivity(intent);
                    }
                }
            }
        });


        //you can implement a list view by using  the following code

//        random_workout_list=findViewById(R.id.randomlistview);
//
//        Randomworkoutadapter mrandomworkoutadapter = new Randomworkoutadapter(this,R.layout.workoutview,workoutnamesarray,workoutimages,duration);
//        random_workout_list.setAdapter(mrandomworkoutadapter);

    }
}

//you can create a list adapter for the list view by using the following code

// class Randomworkoutadapter extends ArrayAdapter {
//    String names[];
//    int images[];
//    int duration[];
//
//
//     public  Randomworkoutadapter(@NonNull Context context, int resource, String names[], int images[], int duration[]) {
//
//
//         super(context, resource,names);
//         this.names=names;
//         this.images=images;
//         this.duration=duration;
//
//     }
//
//     @NonNull
//     @Override
//     public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//       LayoutInflater  layoutInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//         View view=layoutInflater.inflate(R.layout.workoutview ,parent,false);
//         ImageView imageView=view.findViewById(R.id.random_image_view);
//         TextView textView=view.findViewById(R.id.random_workout_name);
//         TextView textView1=view.findViewById(R.id.random_workout_duration);
//         imageView.setImageResource(images[position]);
//         textView.setText(names[position]);
//         textView1.setText((duration[position]/1000)+"sec");
//
//
//         return view;
//     }
// }