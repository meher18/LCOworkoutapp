package com.example.lcoworkoutapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.specials.out.TakingOffAnimator;

import java.util.ArrayList;
import java.util.Random;

public class randomworkoutreal extends AppCompatActivity {
    NotificationManagerCompat notificationManagerCompat;
    int timeinseconds;
    TextView timer;
    ImageView imageView;
    ProgressBar progressBar;
    ArrayList<Integer> list = new ArrayList<>();
    int next = 0;
    int totaltime;
    int k = 0;
    Random positionsong = new Random();
    ArrayList<Integer> timeArray = new ArrayList<>();
    int songs[] = {R.raw.alone, R.raw.joinedaudio, R.raw.songpop34, R.raw.songrock14, R.raw.songrock18, R.raw.upfromtheashes, R.raw.faded};
    TextView workoutname;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    int noofsets = 1;
    int counterforset = 0;
    int breaktime;
    Notification notification;
    Boolean flagfornotification=false;
    public final static String CHANNEL_ID = "channel1";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomworkoutreal);

        timer = findViewById(R.id.Timer);
        imageView = findViewById(R.id.imageView2);
        progressBar = findViewById(R.id.progress_bar);
        workoutname = findViewById(R.id.workoutname);
        mediaPlayer = new MediaPlayer();

        notificationManagerCompat = NotificationManagerCompat.from(this);
        Intent intent = getIntent();
        list = intent.getIntegerArrayListExtra("list_of_random");
        noofsets = Integer.parseInt(intent.getStringExtra("sets"));


        totaltime = 0;

        for (int i = 0; i < list.size(); i++) {
            totaltime = totaltime + randomWorkout.duration[list.get(i)];
            timeArray.add(randomWorkout.duration[list.get(i)]);

        }
        timeinseconds = timeArray.get(next) / 1000;
        progressBar.setMax(timeinseconds);
        breaktime = 40000 * timeArray.size();

//        while (noofsets > 0) {

        int song = positionsong.nextInt(7);
        Toast.makeText(this, "" + song, Toast.LENGTH_SHORT).show();


        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs[song]);
        mediaPlayer.start();
//           while(set<list.size()) {
//          timeinseconds=timeArray.get(set)/1000;
//               Toast.makeText(this, "workout for :"+(timeArray.get(set) / 1000) + " sec", Toast.LENGTH_SHORT).show();
        imageView.setImageResource(randomWorkout.workoutimages[list.get(next)]);
        workoutname.setText(randomWorkout.workoutnamesarray[list.get(next)]);

        countDownTimer = new CountDownTimer((totaltime + breaktime) * noofsets, 1000) {

            @Override
            public void onTick(long l) {


                timeinseconds--;
                timer.setText("00:" + timeinseconds);
//                if(next==0 && counterforset>=0)
//                {
//
//
//                }
                k++;
                progressBar.setProgress(k);
                if (k == (timeArray.get(next) / 1000) && timeinseconds == 0) {

                    mediaPlayer.pause();
                    imageView.setImageResource(R.drawable.breaktime);
                    workoutname.setText("BREAK TIME ");
                    k = 0;
                    timeinseconds = 40;
                    progressBar.setMax(40);
                    progressBar.setProgress(0);
                    Toast.makeText(randomworkoutreal.this, "take a break", Toast.LENGTH_SHORT).show();

                }


//                    this.cancel();
//                    Toast.makeText(randomworkoutreal.this, "no more breaks", Toast.LENGTH_SHORT).show();

                if (timeinseconds == 0 && k == 40) {

                    next++;
                    k = 0;
                    counterforset += 1;


                    if (next < timeArray.size()) {
                        mediaPlayer.start();
                        timeinseconds = timeArray.get(next) / 1000;
                        progressBar.setMax(timeinseconds);

                        imageView.setImageResource(randomWorkout.workoutimages[list.get(next)]);
                        workoutname.setText(randomWorkout.workoutnamesarray[list.get(next)]);

                    } else {
                        Toast.makeText(randomworkoutreal.this, "all workout finished", Toast.LENGTH_SHORT).show();

                    }


                }
//                ((totaltime +breaktime) / 1000) / noofsets
                if (counterforset == timeArray.size()) {
                    mediaPlayer.start();
                    counterforset = 0;
                    Toast.makeText(randomworkoutreal.this, "set finished", Toast.LENGTH_SHORT).show();
                    next = 0;
                    timeinseconds = timeArray.get(next) / 1000;
                    progressBar.setMax(timeinseconds);
                    progressBar.setProgress(0);
                    k = 0;
                    imageView.setImageResource(randomWorkout.workoutimages[list.get(next)]);
                    workoutname.setText(randomWorkout.workoutnamesarray[list.get(next)]);


                }
//                    if(counterforset>=totaltime/1000)
//                    {
//                        Toast.makeText(randomworkoutreal.this, "no sets more", Toast.LENGTH_SHORT).show();
//                    }


            }

            @Override
            public void onFinish() {
                mediaPlayer.stop();
//                      set=list.size();
                flagfornotification=true;
                timeinseconds = 0;
                Toast.makeText(randomworkoutreal.this, "finished", Toast.LENGTH_SHORT).show();

            }
        };
        countDownTimer.start();
        Toast.makeText(this, noofsets + " sets", Toast.LENGTH_SHORT).show();
//    }

//            if (noofsets == 0) {
//                Toast.makeText(this, "all sets finished", Toast.LENGTH_SHORT).show();
//            }
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "channel 1", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("notification channel for leaving the workout in the middle...");

            notificationChannel.setLightColor(Color.RED);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);


            notification = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_warning_black_24dp).setContentTitle("warning")
                    .setContentText("please don't leave in the middle of the random workout !")
                    .setCategory(NotificationCompat.CATEGORY_PROGRESS)
                    .setColor(Color.RED)

                    .build();


        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer.isPlaying()) {
            Toast.makeText(this, "Have a good day", Toast.LENGTH_SHORT).show();
            mediaPlayer.stop();
            countDownTimer.cancel();
        } else {
            countDownTimer.cancel();
            Toast.makeText(this, "Have a good day", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (flagfornotification==false) {
            notificationManagerCompat.notify(1, notification);
        }

    }
}



