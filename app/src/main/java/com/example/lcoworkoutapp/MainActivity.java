package com.example.lcoworkoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Button random_button;
    Button daily_button;
    BottomNavigationView bottomNavigationView;
Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random_button = findViewById(R.id.random_button);
        daily_button = findViewById(R.id.daily_button);


     getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new homefragement()).commit();


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               menuItem.setChecked(true);

                switch (menuItem.getItemId())
                {
                    case R.id.home :  fragment =new homefragement();


                    break;
                    case R.id.about:  fragment=new aboutusfragment();


                        break;
                    case R.id.developer:fragment=new developerfragement();

                    break;
                }


                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();






                return false;
            }
        });
        //on click to send different intents

//        random_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,randomWorkout.class);
//                startActivity(intent);
//            }
//        });
//        daily_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//             Intent intent=new Intent(MainActivity.this,daywiseworkout.class);
//             startActivity(intent);
//            }
//        });
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(this, "paused", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(this, "stoped", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public boolean isFinishing() {
//        Toast.makeText(this, "finishing", Toast.LENGTH_SHORT).show();
//        return super.isFinishing();
//
//    }
}
