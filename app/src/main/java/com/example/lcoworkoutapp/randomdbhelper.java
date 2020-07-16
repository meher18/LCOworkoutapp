package com.example.lcoworkoutapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class randomdbhelper extends SQLiteOpenHelper {
    public static final int VERSION=1;
    public static final String DATABASE_NAME="workoutsDb";

    Context context;
    SQLiteDatabase db;
    public randomdbhelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db=sqLiteDatabase;
        String sql="create table workouts"+" ("+
                randomworkouttable.table._ID +"INTEGER PRIMARY KEY AUTOINCREMENT ,"+


                randomworkouttable.table.WORKOUT_NAME +" TEXT,"+
                randomworkouttable.table.WORKOUT_DURATION + " INT,"+
                randomworkouttable.table.WORKOUT_NAME+" TEXT "+
                ")"

                ;


         db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {




    }
//    public List<randomWorkout> getallworkouts()
//    {
//
//    }
    public void insertworkouts(String name,int duration,String image_name)
    {
        ContentValues cv=new ContentValues();
        cv.put(randomworkouttable.table.WORKOUT_NAME,name);
        cv.put(randomworkouttable.table.WORKOUT_DURATION,duration);
        cv.put(randomworkouttable.table.WORKOUT_IMAGE,image_name);
        db.insert("workouts",null,cv);

    }

}
