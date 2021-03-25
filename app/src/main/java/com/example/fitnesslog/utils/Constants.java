package com.example.fitnesslog.utils;

import com.example.fitnesslog.R;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static List<ExerciseItem> getExerciseItemList(){
        List<ExerciseItem> exerciseItemList = new ArrayList<>();
        exerciseItemList.add(new ExerciseItem("Bench Press", R.drawable.deadlift));
        exerciseItemList.add(new ExerciseItem("Bench Press", R.drawable.deadlift));
        exerciseItemList.add(new ExerciseItem("Bench Press", R.drawable.deadlift));
        exerciseItemList.add(new ExerciseItem("Bench Press", R.drawable.deadlift));
        return exerciseItemList;
    }

}
