package com.example.fitnesslog.utils;

import com.example.fitnesslog.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";
    public static List<ExerciseItem> getExerciseItemList(String tabName){
        List<ExerciseItem> res = new ArrayList<>();
        switch (tabName){
            case "Cardio":
                res = getCardioItemList();
                break;
            default:
                res = getBarbellItemList();
                break;
        }
        return res;
    }

    private static List<ExerciseItem> getCardioItemList(){
        List<ExerciseItem> exerciseItemList = new ArrayList<>();
        exerciseItemList.add(new ExerciseItem("Running Jogging Treadmill", R.drawable.running_jogging_treadmill, false, false,true,true ));
        exerciseItemList.add(new ExerciseItem("Stationary Bike", R.drawable.stationary_bike, true, false,true,true));
        exerciseItemList.add(new ExerciseItem("Rowing Machine", R.drawable.rowing_machine, true, false,true,true));
        exerciseItemList.add(new ExerciseItem("Elliptical Machine", R.drawable.elliptical_machine, true, false,true,true));
        return exerciseItemList;
    }
    private static List<ExerciseItem> getBarbellItemList(){
        List<ExerciseItem> exerciseItemList = new ArrayList<>();
        exerciseItemList.add(new ExerciseItem("Barbell Bench Press", R.drawable.barbell_bench_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Barbell Deadlifts", R.drawable.barbell_deadlifts, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Barbell Shoulder Press", R.drawable.barbell_shoulder_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Barbell Squats", R.drawable.barbell_squats, true, true,false,false));
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
