package com.example.lcoworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lcoworkoutapp.MainActivity;
import com.example.lcoworkoutapp.R;
import com.example.lcoworkoutapp.daywiseworkout;
import com.example.lcoworkoutapp.randomWorkout;

public class homefragement extends Fragment {
    LayoutInflater layoutInflater;
    Button button;
    Button button1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View v = inflater.inflate(R.layout.homefragment,container,false);
      button=v.findViewById(R.id.random_button);
      button1=v.findViewById(R.id.daily_button);

      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent  =new Intent(getContext(), randomWorkout.class);
            startActivity(intent);
          }
      });
      button1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent  =new Intent(getContext(), daywiseworkout.class);
              startActivity(intent);
          }
      });

        return v;
    }
}
