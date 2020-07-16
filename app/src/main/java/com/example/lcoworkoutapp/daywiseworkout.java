package com.example.lcoworkoutapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.icu.util.IndianCalendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.textclassifier.ConversationAction;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.GregorianCalendar;

public class daywiseworkout extends AppCompatActivity {


    static String workoutnamesarray[] = {"plank", "crunches", "straight knee crunches", "bends", "bends", "dumbbell rows", "dumbbell curls", "knee exercise", "yoga", "chest pull", "incline bench press", "bench press", "shoulder flies", "crunches"};
    static String workoutnamedescription[] = {"plank helps to strengthen you chest and abs", "it's great for your abs", "it's great for the full body stretch", "help to lower the belly fat", "h", "dumbbell rows", "gives you the perfect biceps shape", "good for the knees and the legs", "perfect choice for the full body", "it is great for the back and the chest ", "focus on the core chest", "great for the chest", "build strong shoulders", "it'a a great exercise for the abs and body"};
    static int workoutimages[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
            R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n};
    static int duration[] = {60000, 35000, 45000, 45000, 30000, 45000, 35000, 35000, 45000, 35000, 35000, 35000, 45000, 35000};
    int days[] = {1, 2, 3, 4, 5, 6, 7};
    int day1[] = {6, 3, 4, 8, 12};
    int day2[] = {0, 3, 10, 13, 2};
    int day3[] = {6, 3, 8, 9, 11};
    int day4[] = {5, 8, 3, 7, 11};
    int day5[] = {11, 10, 4, 2, 0};
    int day6[] = {1, 8, 7, 5, 9};
    TextView textView;
     FloatingActionButton music_select;
    RecyclerView recyclerView;
    dailywiseworkoutadapter dailywiseworkoutadapter;
    Button workout_button;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daywiseworkout);
        Date date = new Date();
        int day = date.getDay();
//        Toast.makeText(this, "" + date.getDay(), Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.daily_workout_recycler_view);
        textView = findViewById(R.id.daily_text_view);
        workout_button = findViewById(R.id.workoutbutton);
        music_select=findViewById(R.id.music_select_button);
        int currentday = day;
        switch (currentday) {
            case 1:
                textView.setText("MONDAY");

                create_recycler_view(day1);

                break;
            case 2:
                textView.setText("TUESDAY");

                create_recycler_view(day2);
                break;
            case 3:
                textView.setText("WEDNESDAY");

                create_recycler_view(day3);
                break;
            case 4:
                textView.setText("THURSDAY");

                create_recycler_view(day4);
                break;
            case 5:
                textView.setText("FRIDAY");

                create_recycler_view(day5);
                break;
            case 6:
                textView.setText("SATURDAY");

                create_recycler_view(day6);
                break;
            case 0:
                TextView textView1=findViewById(R.id.textView4);
                textView1.setText("Take a break today ! lets workout tommorow .");
                recyclerView.setVisibility(View.INVISIBLE);

                workout_button.setVisibility(View.INVISIBLE);

                textView.setText("SUNDAY NO WORK OUT");

                break;
            default:
                Toast.makeText(this, "something rong", Toast.LENGTH_SHORT).show();


        }
        music_select.setRippleColor(Color.GREEN);
       //this is where you gonna select the music
        music_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent =new Intent(daywiseworkout.this,selectsongs.class);
              startActivity(intent);
            }
        });

    }

    public void create_recycler_view(final int arr[]) {
        dailywiseworkoutadapter = new dailywiseworkoutadapter(this, arr, workoutnamesarray, workoutimages, duration,workoutnamedescription);
        recyclerView.setLayoutManager(new LinearLayoutManager(daywiseworkout.this));
        recyclerView.setAdapter(dailywiseworkoutadapter);
        workout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(daywiseworkout.this,dailyworkoutreal.class);
                intent.putExtra("dailyworkouts",arr);
                startActivity(intent);
            }
        });

    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode==1 && resultCode==RESULT_OK){
//            Toast.makeText(getApplicationContext(),"ohh you selected songs fine then",Toast.LENGTH_LONG).show();
//        }
//    }
}
