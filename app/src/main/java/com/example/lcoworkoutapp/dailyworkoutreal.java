package com.example.lcoworkoutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class dailyworkoutreal extends AppCompatActivity {
     boolean flagfortheselectedsongs=false;
    int timeinseconds;
    TextView timer;
    ImageView imageView;
    ProgressBar progressBar;
    int list[];
    int set = 0;
    int totaltime;
    int k = 0;
    Random positionsong= new Random();
    ArrayList<Integer> timeArray = new ArrayList<>();
    int songs[]={R.raw.alone,R.raw.joinedaudio,R.raw.songpop34,R.raw.songrock14,R.raw.songrock18,R.raw.upfromtheashes,R.raw.idontcare,R.raw.faded};
    TextView workoutname;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    SharedPreferences preferences;
    int positionfortheselectedsong=0;
    String allselected[];
    String allselectedpositions;
    Boolean flagfornotification=false;
    public static String CHANNEL_ID2="channel2";
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyworkoutreal);

        preferences=getSharedPreferences(selectsongs.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
         allselectedpositions=preferences.getString("allselectedpositions","");
         allselected=allselectedpositions.split(",");
        if(allselected.length!=0 && allselected.length==5) {
            flagfortheselectedsongs=true;
            Toast.makeText(this, "wow! lets start .", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"playing randomly selected songs",Toast.LENGTH_LONG).show();
        }

        //creating the notification compact class for building the notification
        notificationManagerCompat=NotificationManagerCompat.from(this);


        timer = findViewById(R.id.Timer2);
        imageView = findViewById(R.id.imageView3);
        progressBar = findViewById(R.id.progress_bar2);
        workoutname=findViewById(R.id.workoutname2);
        mediaPlayer=new MediaPlayer();



        Intent intent = getIntent();
        list = intent.getIntArrayExtra("dailyworkouts");
        totaltime = 0;

        for (int i = 0; i < list.length; i++) {
            totaltime = totaltime + daywiseworkout.duration[list[i]];
            timeArray.add(daywiseworkout.duration[list[i]]);

        }
        timeinseconds = timeArray.get(set) / 1000;
        progressBar.setMax(timeinseconds);
        final int breaktime = 40000 * 4;

        int song=positionsong.nextInt(7);
//        Toast.makeText(this, ""+song, Toast.LENGTH_SHORT).show();

//          positionfortheselectedsong=0;

        if (flagfortheselectedsongs==true){
            mediaPlayer=MediaPlayer.create(getApplicationContext(),songs[Integer.parseInt(allselected[positionfortheselectedsong])]);
        }
        else{
            mediaPlayer=MediaPlayer.create(getApplicationContext(),songs[song]);
        }
        mediaPlayer.start();

        imageView.setImageResource(daywiseworkout.workoutimages[list[set]]);
        workoutname.setText(daywiseworkout.workoutnamesarray[list[set]]);
        countDownTimer = new CountDownTimer(totaltime + breaktime, 1000) {


            @Override
            public void onTick(long l) {
                k++;
                timeinseconds--;
                timer.setText("00:" + timeinseconds );

                progressBar.setProgress(k);
                if (k == (timeArray.get(set) / 1000) && timeinseconds == 0) {
                    mediaPlayer.pause();

                    k = 0;
                    timeinseconds = 40;
                    progressBar.setMax(40);
                    progressBar.setProgress(0);
                    imageView.setImageResource(R.drawable.breaktime);
                    workoutname.setText("It's Break time !");
                    Toast.makeText(dailyworkoutreal.this, "take a break", Toast.LENGTH_SHORT).show();


                }


//                    this.cancel();
//                    Toast.makeText(randomworkoutreal.this, "no more breaks", Toast.LENGTH_SHORT).show();

                if (timeinseconds == 0 && k == 40) {

                    set++;
                    positionfortheselectedsong++;
                    k = 0;

                    if (set < timeArray.size()) {
                        progressBar.setProgress(0);
                        if(flagfortheselectedsongs==true)
                        { mediaPlayer.stop();
                          mediaPlayer=MediaPlayer.create(getApplicationContext(),songs[Integer.parseInt(allselected[positionfortheselectedsong])]);
                          mediaPlayer.start();
                        }
                        else{
                            mediaPlayer.start();

                        }
                        timeinseconds = timeArray.get(set) / 1000;
                        progressBar.setMax(timeinseconds);
                        imageView.setImageResource(daywiseworkout.workoutimages[list[set]]);
                        workoutname.setText(daywiseworkout.workoutnamesarray[list[set]]);

                    } else {
                        progressBar.setProgress(0);
                        Toast.makeText(dailyworkoutreal.this, "all workout finished", Toast.LENGTH_SHORT).show();
                        this.cancel();
                    }


                }


            }

            @Override
            public void onFinish() {
                mediaPlayer.stop();
                flagfornotification=true;
//                      set=list.size();
                timeinseconds = 0;
                Toast.makeText(dailyworkoutreal.this, "finished", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();

//    }

        //creating the notification channel for the alert message
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new
                    NotificationChannel(CHANNEL_ID2, "notification for the ", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("this is the notification channel for the daily workout ");
            notificationChannel.setName("channel 2");
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

            notification= new NotificationCompat.Builder(this,CHANNEL_ID2)
                    .setSmallIcon(R.drawable.ic_warning_black_24dp)
                    .setContentTitle("warning")
                    .setContentText("please don't leave in the middle of the daily workout !")
                    .setColor(Color.RED)
                    .build();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            countDownTimer.cancel();
        }
        else{
            countDownTimer.cancel();


        }
        Toast.makeText(this, "Have a wonderful weekend", Toast.LENGTH_SHORT).show();

    }



    @Override
    protected void onPause() {
        super.onPause();
        if(flagfornotification==false){
            notificationManagerCompat.notify(1,notification);

        }

    }


}
