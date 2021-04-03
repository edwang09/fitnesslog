package com.example.fitnesslog.utils;

import com.example.fitnesslog.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public static List<ExerciseItem> getExerciseItemList(){
        List<ExerciseItem> exerciseItemList = new ArrayList<>();
        exerciseItemList.add(new ExerciseItem("Bench Press", R.drawable.deadlift));
        exerciseItemList.add(new ExerciseItem("Bench Press", R.drawable.deadlift));
        exerciseItemList.add(new ExerciseItem("Bench Press", R.drawable.deadlift));
        exerciseItemList.add(new ExerciseItem("Bench Press", R.drawable.deadlift));
        return exerciseItemList;
    }
    public static Map<String, String> getScheduleMap(){
        Map<String, String> scheduleMap = new HashMap<>();
        scheduleMap.put("Mon", "MON");
        scheduleMap.put("Tue", "TUE");
        scheduleMap.put("Wed", "WED");
        scheduleMap.put("Thu", "THU");
        scheduleMap.put("Fri", "FRI");
        scheduleMap.put("Sat", "SAT");
        scheduleMap.put("Sun", "SUM");
        scheduleMap.put("On demand", "DEM");
        return scheduleMap;
    }
}
