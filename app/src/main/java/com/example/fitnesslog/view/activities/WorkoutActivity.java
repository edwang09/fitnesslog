package com.example.fitnesslog.view.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.ActivityMainBinding;
import com.example.fitnesslog.databinding.ActivityWorkoutBinding;

public class WorkoutActivity extends AppCompatActivity {
    ActivityWorkoutBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //view binding
        mBinding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);


    }



}