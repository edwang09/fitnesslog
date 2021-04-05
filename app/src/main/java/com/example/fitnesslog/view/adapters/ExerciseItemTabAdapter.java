package com.example.fitnesslog.view.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fitnesslog.view.fragments.BodyChartFatFragment;
import com.example.fitnesslog.view.fragments.BodyChartWeightFragment;
import com.example.fitnesslog.view.fragments.SelectExerciseFragment;
import com.example.fitnesslog.view.fragments.SelectExerciseTabFragment;

public class ExerciseItemTabAdapter extends FragmentPagerAdapter {
    SelectExerciseFragment fragment;
    public ExerciseItemTabAdapter(@NonNull FragmentManager fm, int behavior, SelectExerciseFragment fragment) {
        super(fm, behavior);
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new SelectExerciseTabFragment(getPageTitle(position).toString(), fragment);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
                if (position == 0){
            return "Cardio";
        }else if(position == 1){
            return "Dumbbell";
        }else if(position == 2){
            return "Barbell";
        }else if(position == 3){
            return "Machine";
        }else if(position == 4){
            return "Self Weight";
        }
        return "";
    }

}