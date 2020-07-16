package com.example.lcoworkoutapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class selectsongs extends AppCompatActivity {
    public static int songs[] = {R.raw.alone, R.raw.joinedaudio, R.raw.songpop34, R.raw.songrock14, R.raw.songrock18, R.raw.upfromtheashes, R.raw.idontcare, R.raw.faded};
    String songnames1[] = {"Alone", "Join audio", "song 34", "song 14", "song 18", "Up from the theases", "I don't care", "faded(Alan Walker)"};
    static String workoutnamesarray[] = {"plank", "crunches", "straight knee crunches", "bends", "bends", "dumbell rows", "dumbel curls", "knee excercise", "yoga", "chest pull", "incline bench presh", "bech press", "shoulder flyies", "crunches"};
    static int workoutimages[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n};
    ExpandableListView expandableListView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;

    ListView listView1;
    ListView listView2;
    ListView listView3;
    ListView listView4;
    ListView listView5;


    CheckBox checkBox;
    SharedPreferences preferences;
    static String SHARED_PREFERENCES_NAME = "selectedsongs";

    ArrayAdapter arrayAdapter1;
    ArrayAdapter arrayAdapter2;
    ArrayAdapter arrayAdapter3;
    ArrayAdapter arrayAdapter4;
    ArrayAdapter arrayAdapter5;

    FloatingActionButton floatingActionButton;
    SharedPreferences.Editor editor;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectsongs);

        textView1 = findViewById(R.id.excercise1);
        textView2 = findViewById(R.id.excercise2);
        textView3 = findViewById(R.id.excercise3);
        textView4 = findViewById(R.id.excercise4);
        textView5 = findViewById(R.id.excercise5);

        listView1 = findViewById(R.id.select_song_listview1);
        listView2 = findViewById(R.id.select_song_listview2);
        listView3 = findViewById(R.id.select_song_listview3);
        listView4 = findViewById(R.id.select_song_listview4);
        listView5 = findViewById(R.id.select_song_listview5);

        floatingActionButton = findViewById(R.id.savemusic);


        arrayAdapter1 = new Adapterforlist1(this, R.layout.viewforselectsongs, songnames1);
        arrayAdapter2 = new Adapterforlist2(this, R.layout.viewforselectsongs, songnames1);
        arrayAdapter3 = new Adapterforlist3(this, R.layout.viewforselectsongs, songnames1);
        arrayAdapter4 = new Adapterforlist4(this, R.layout.viewforselectsongs, songnames1);
        arrayAdapter5 = new Adapterforlist5(this, R.layout.viewforselectsongs, songnames1);
        listView1.setAdapter(arrayAdapter1);
        listView2.setAdapter(arrayAdapter2);
        listView3.setAdapter(arrayAdapter3);
        listView4.setAdapter(arrayAdapter4);
        listView5.setAdapter(arrayAdapter5);

        floatingActionButton.setRippleColor(Color.WHITE);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                addsongs();
            }
        });


    }

    public void addsongs() {
        preferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        editor = preferences.edit();
        int flag_check_all_are_selected = 0;
        ArrayList<Integer> arrayList1 = Adapterforlist1.songpostions;
        ArrayList<Integer> arrayList2 = Adapterforlist2.songpostions;
        ArrayList<Integer> arrayList3 = Adapterforlist3.songpostions;
        ArrayList<Integer> arrayList4 = Adapterforlist4.songpostions;
        ArrayList<Integer> arrayList5 = Adapterforlist5.songpostions;

        ArrayList<Integer> arrayListsongsfinal = new ArrayList<>();
        if (arrayList1.isEmpty()) {
            flag_check_all_are_selected+= 0;
            Toast.makeText(this, "select songs dude", Toast.LENGTH_SHORT).show();
        } else {
            flag_check_all_are_selected+= 1;
            arrayListsongsfinal.add(arrayList1.get(0));
        }
        if (arrayList2.isEmpty()) {
            flag_check_all_are_selected+= 0;
            Toast.makeText(this, "select songs dude", Toast.LENGTH_SHORT).show();
        } else {
            flag_check_all_are_selected+= 1;
            arrayListsongsfinal.add(arrayList2.get(0));
        }
        if (arrayList3.isEmpty()) {
            flag_check_all_are_selected+= 0;
            Toast.makeText(this, "select songs dude", Toast.LENGTH_SHORT).show();
        } else {
            flag_check_all_are_selected+= 1;
            arrayListsongsfinal.add(arrayList3.get(0));

        }
        if (arrayList4.isEmpty()) {
            flag_check_all_are_selected+= 0;
            Toast.makeText(this, "select songs dude", Toast.LENGTH_SHORT).show();
        } else {
            flag_check_all_are_selected+= 1;
            arrayListsongsfinal.add(arrayList4.get(0));
        }
        if (arrayList5.isEmpty()) {
            flag_check_all_are_selected+= 0;
            Toast.makeText(this, "select songs dude", Toast.LENGTH_SHORT).show();
        } else {
            flag_check_all_are_selected+= 1;
            arrayListsongsfinal.add(arrayList5.get(0));

        }


        if (arrayListsongsfinal.size() != 0 && flag_check_all_are_selected == 5) {

            String a = "";
            for (int i = 0; i < arrayListsongsfinal.size(); i++) {
                if (i == 0) {
                    a = arrayListsongsfinal.get(i).toString();
                } else {
                    a += "," + arrayListsongsfinal.get(i);
                }
            }
            editor.putString("allselectedpositions", a);
            editor.apply();
            arrayList1.clear();
            arrayList2.clear();
            arrayList3.clear();
            arrayList4.clear();
            arrayList5.clear();
            Adapterforlist1.songpostions.clear();
            Adapterforlist2.songpostions.clear();
            Adapterforlist3.songpostions.clear();
            Adapterforlist4.songpostions.clear();
            Adapterforlist5.songpostions.clear();
//            Toast.makeText(this, " lets go on " , Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(selectsongs.this,daywiseworkout.class);
//            startActivity(intent);
            flag_check_all_are_selected=0;
            onBackPressed();
        } else {
            Toast.makeText(this, "Please select the music \nselect one for each excercise", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(getApplicationContext());
        menuInflater.inflate(R.menu.selectsongs, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            preferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);

            if (preferences.getString("allselectedpositions", "") == "") {
                Toast.makeText(this, "no saved songs ", Toast.LENGTH_SHORT).show();
            } else {
                editor = preferences.edit();
                editor.putString("allselectedpositions", "");
                editor.apply();
                Toast.makeText(this, "Songs not saved", Toast.LENGTH_SHORT).show();
            }

        }

        if (item.getItemId()==R.id.playsongs)
        {
            Intent intent=new Intent(selectsongs.this,checksongs.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}

class Adapterforlist1 extends ArrayAdapter {
//     MediaPlayer mediaPlayer;

    int songs[];
    String songnames[];
    TextView textView;
    CheckBox checkBox;
    static ArrayList<Integer> songpostions = new ArrayList<>();

    Context context;
    LayoutInflater layoutInflater;
    int position1;
    Boolean flag=true;

    public Adapterforlist1(@NonNull Context context, int resource, String songsnames[]) {
        super(context, resource, songsnames);
        this.songs = songs;
        this.songnames = songsnames;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewforselectsongs, parent, false);
        textView = view.findViewById(R.id.songcheckname);
        checkBox = view.findViewById(R.id.songcheckbox);


        if (checkBox.isChecked()) {

        }
        textView.setText(songnames[position]);

//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//
//
//                if(compoundButton.isChecked())
//                {
//
//                    Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
////                    if(!checkBox.isChecked())
////                    {
////                        if(songpostions!=null)
////                        {
////                            songpostions.remove(position1);
////                        }
////                    }
//                    songpostions.add(position1);
//                    Toast.makeText(getContext(),songpostions+" ", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox = view.findViewById(R.id.songcheckbox);

                if (checkBox.isChecked()) {
                     position1=position;

                    if (!songpostions.isEmpty()) {
                        songpostions.clear();
                        songpostions.add(position);
//                        if (mediaPlayer.isPlaying() )
//                        {
//                            mediaPlayer.stop();
//                            mediaPlayer=MediaPlayer.create(getContext(),selectsongs.songs[position]);
//                            mediaPlayer.start();
//
//                        }
//                        else{
//                            mediaPlayer=MediaPlayer.create(getContext(),selectsongs.songs[position]);
//                            mediaPlayer.start();
//                        }




                    } else {
//                        mediaPlayer=MediaPlayer.create(getContext(),selectsongs.songs[position]);
//
//                        mediaPlayer.start();
                        songpostions.add(position);
                    }

                }
//                else{
//                    if (position1==position) {
//                        mediaPlayer.stop();
//                    }
//                }
//                Toast.makeText(getContext(), songpostions + " ", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    static public ArrayList get() {

        return songpostions;
    }
}


class Adapterforlist2 extends ArrayAdapter {
//    MediaPlayer mediaPlayer;
    int songs[];
    String songnames[];
    TextView textView;
    CheckBox checkBox;
    static ArrayList<Integer> songpostions = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int position1;

    public Adapterforlist2(@NonNull Context context, int resource, String songsnames[]) {

        super(context, resource, songsnames);
        this.songs = songs;
        this.songnames = songsnames;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewforselectsongs, parent, false);
        textView = view.findViewById(R.id.songcheckname);
        checkBox = view.findViewById(R.id.songcheckbox);
        position1 = position;

        if (checkBox.isChecked()) {

        }
        textView.setText(songnames[position]);


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox = view.findViewById(R.id.songcheckbox);

                if (checkBox.isChecked()) {


                    if (!songpostions.isEmpty()) {
                        songpostions.clear();
                        songpostions.add(position);


//                        if (mediaPlayer.isPlaying() )
//                        {
//                            mediaPlayer.stop();
//                            mediaPlayer=MediaPlayer.create(getContext(),selectsongs.songs[position]);
//                            mediaPlayer.start();
//
//                        }
//                        else{
//                            mediaPlayer=MediaPlayer.create(getContext(),selectsongs.songs[position]);
//                            mediaPlayer.start();

//                        }
                   }
                   else {
                             songpostions.add(position);
//
                   }

                }
//                Toast.makeText(getContext(), songpostions + " ", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    public ArrayList get() {

        return songpostions;
    }
}


class Adapterforlist3 extends ArrayAdapter {
    int songs[];
    String songnames[];
    TextView textView;
    CheckBox checkBox;
    static ArrayList<Integer> songpostions = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int position1;

    public Adapterforlist3(@NonNull Context context, int resource, String songsnames[]) {
        super(context, resource, songsnames);
        this.songs = songs;
        this.songnames = songsnames;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewforselectsongs, parent, false);
        textView = view.findViewById(R.id.songcheckname);
        checkBox = view.findViewById(R.id.songcheckbox);
        position1 = position;
        if (checkBox.isChecked()) {

        }

        textView.setText(songnames[position]);


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox = view.findViewById(R.id.songcheckbox);

                if (checkBox.isChecked()) {
                    if (!songpostions.isEmpty()) {
                        songpostions.clear();
                        songpostions.add(position);
                    } else {
                        songpostions.add(position);
                    }

                }
//                Toast.makeText(getContext(), songpostions + " ", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    public ArrayList get() {

        return songpostions;
    }
}


class Adapterforlist4 extends ArrayAdapter {
    MediaPlayer mediaPlayer;
    int songs[];
    String songnames[];
    TextView textView;
    CheckBox checkBox;
    static ArrayList<Integer> songpostions = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int position1;

    public Adapterforlist4(@NonNull Context context, int resource, String songsnames[]) {
        super(context, resource, songsnames);
        this.songs = songs;
        this.songnames = songsnames;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewforselectsongs, parent, false);
        textView = view.findViewById(R.id.songcheckname);
        checkBox = view.findViewById(R.id.songcheckbox);
        position1 = position;

        if (checkBox.isChecked()) {

        }
        textView.setText(songnames[position]);


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox = view.findViewById(R.id.songcheckbox);

                if (checkBox.isChecked()) {
                    if (!songpostions.isEmpty()) {
                        songpostions.clear();
                        songpostions.add(position);
                    } else {
                        songpostions.add(position);
                    }

                }
//                Toast.makeText(getContext(), songpostions + " ", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    public ArrayList get() {

        return songpostions;
    }
}


class Adapterforlist5 extends ArrayAdapter {
    MediaPlayer mediaPlayer;
    int songs[];
    String songnames[];
    TextView textView;
    CheckBox checkBox;
    static ArrayList<Integer> songpostions = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int position1;
    boolean flag = true;

    public Adapterforlist5(@NonNull Context context, int resource, String songsnames[]) {
        super(context, resource, songsnames);
        this.songs = songs;
        this.songnames = songsnames;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewforselectsongs, parent, false);
        textView = view.findViewById(R.id.songcheckname);
        checkBox = view.findViewById(R.id.songcheckbox);

        if (checkBox.isChecked()) {

        }

        textView.setText(songnames[position]);


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox = view.findViewById(R.id.songcheckbox);
                position1 = position;
                if (checkBox.isChecked()) {


                    if (!songpostions.isEmpty()) {
                        songpostions.clear();
                        songpostions.add(position);
                    } else {
                        songpostions.add(position);
                    }

                }

//                Toast.makeText(getContext(), songpostions + " ", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    public ArrayList get() {

        return songpostions;
    }
}