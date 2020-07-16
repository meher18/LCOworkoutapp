package com.example.lcoworkoutapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class checksongs extends AppCompatActivity {
    public static int songs[] = {R.raw.alone, R.raw.joinedaudio, R.raw.songpop34, R.raw.songrock14, R.raw.songrock18, R.raw.upfromtheashes, R.raw.idontcare, R.raw.faded};
    String songnames1[] = {"Alone", "Join audio", "song 34", "song 14", "song 18", "Up from the theases", "I don't care", "faded(Alan Walker)"};
    ArrayAdapter arrayAdapter1;
 ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checksongs);
        listView=findViewById(R.id.checksongslistview);
        arrayAdapter1=new Adapterforlist6(this,R.layout.checksongsview,songnames1);
        listView.setAdapter(arrayAdapter1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Adapterforlist6.mediaPlayer.isPlaying()) {
            Adapterforlist6.mediaPlayer.stop();
        }
    }
}

//adapter for the list view

 class Adapterforlist6 extends ArrayAdapter {
   static MediaPlayer mediaPlayer;
    int songs[];
    String songnames[];
    TextView textView;
    CheckBox checkBox;
    static ArrayList<Integer> songpostions = new ArrayList<>();

    Context context;
    LayoutInflater layoutInflater;
    int position1;
    Boolean flag=true;

    public Adapterforlist6(@NonNull Context context, int resource, String songsnames[]) {
        super(context, resource, songsnames);
        this.songs = songs;
        this.songnames = songsnames;
        this.context = context;


    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.checksongsview, parent, false);
        checkBox = view.findViewById(R.id.songcheckbox2);
        textView = view.findViewById(R.id.songcheckname2);


        if (checkBox.isChecked()) {

        }
        textView.setText(songnames[position]);


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox = view.findViewById(R.id.songcheckbox2);

                if (checkBox.isChecked()) {
                    position1=position;

                    if (!songpostions.isEmpty()) {
                        songpostions.clear();
                        songpostions.add(position);
                        if (mediaPlayer.isPlaying() )
                        {
                            mediaPlayer.stop();
                            mediaPlayer=MediaPlayer.create(getContext(),selectsongs.songs[position]);
                            mediaPlayer.start();

                        }
                        else{
                            mediaPlayer=MediaPlayer.create(getContext(),selectsongs.songs[position]);
                            mediaPlayer.start();
                        }




                    } else {
                        mediaPlayer=MediaPlayer.create(getContext(),selectsongs.songs[position]);

                        mediaPlayer.start();
                        songpostions.add(position);
                    }

                }
                else{
                    if (position1==position) {
                        mediaPlayer.stop();
                    }
                }
//                Toast.makeText(getContext(), songpostions + " ", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    static public ArrayList get() {

        return songpostions;
    }
}
