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
            case "Dumbbell":
                res = getDumbbellItemList();
                break;
            case "Barbell":
                res = getBarbellItemList();
                break;
            case "Machine":
                res = getMachineItemList();
                break;
            case "Self Weight":
                res = getSelfWeightItemList();
                break;
            default:
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
        exerciseItemList.add(new ExerciseItem("Assault Bike", R.drawable.assault_bike, false, false,true,true));
        exerciseItemList.add(new ExerciseItem("Battle Rope Snakes", R.drawable.battle_rope_snakes, false, false,false,true));
        exerciseItemList.add(new ExerciseItem("Burpees", R.drawable.burpees, false, true,false,true));
        exerciseItemList.add(new ExerciseItem("Walking", R.drawable.walking, false, false,true,true));
        exerciseItemList.add(new ExerciseItem("Cycling ", R.drawable.cycling, false, false,true,true));
        exerciseItemList.add(new ExerciseItem("Stairmaster", R.drawable.stairmaster, false, false,true,true));
        exerciseItemList.add(new ExerciseItem("Swimming", R.drawable.swimming, false, false,true,true));
        return exerciseItemList;
    }
    private static List<ExerciseItem> getBarbellItemList(){
        List<ExerciseItem> exerciseItemList = new ArrayList<>();
        exerciseItemList.add(new ExerciseItem("Barbell Bench Press", R.drawable.barbell_bench_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Barbell Deadlifts", R.drawable.barbell_deadlifts, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Barbell Shoulder Press", R.drawable.barbell_shoulder_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Barbell Squats", R.drawable.barbell_squats, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Decline Barbell Bench Press", R.drawable.decline_barbell_bench_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Incline Barbell Bench Press", R.drawable.incline_barbell_bench_press, true, true,false,false));

        exerciseItemList.add(new ExerciseItem("Barbell Curls", R.drawable.barbell_curls, true, true,false,false));

        exerciseItemList.add(new ExerciseItem("Barbell Good Morning", R.drawable.barbell_good_morning, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Barbell Hip Thrusts", R.drawable.barbell_hip_thrusts, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Barbell Lunges", R.drawable.barbell_lunges, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Barbell Shoulder Shrugs", R.drawable.barbell_shoulder_shrugs, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Bent Over Barbell Rows", R.drawable.bent_over_barbell_rows, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Bent Over T-Bar Rows", R.drawable.bent_over_t_bar_rows, true, true,false,false));
        return exerciseItemList;
    }

    private static List<ExerciseItem> getDumbbellItemList(){
        List<ExerciseItem> exerciseItemList = new ArrayList<>();
        exerciseItemList.add(new ExerciseItem("Decline Bench Dumbbell Press", R.drawable.decline_bench_dumbbell_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Decline Dumbbell Flyes", R.drawable.decline_dumbbell_flyes, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Dumbbell Bent Over Lateral Rear Delt Raises", R.drawable.dumbbell_bent_over_lateral_rear_delt_raises, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Dumbbell Flat Bench Press", R.drawable.dumbbell_flat_bench_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Dumbbell Incline Bench Rows", R.drawable.dumbbell_incline_bench_rows, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Dumbbell Lunges", R.drawable.dumbbell_lunges, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Dumbbell Overhead Shoulder Press", R.drawable.dumbbell_overhead_shoulder_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Dumbbell Shrugs", R.drawable.dumbbell_shrugs, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Flat Bench Dumbbell Flyes", R.drawable.flat_bench_dumbbell_flyes, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Dumbbell Weighted Leg Pull-Ins", R.drawable.dumbbell_weighted_leg_pull_ins, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Bulgarian Split Squats", R.drawable.bulgarian_split_squats, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Incline Dumbbell Bench Chest Press", R.drawable.incline_dumbbell_bench_chest_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Incline Bench Dumbbell Flyes", R.drawable.incline_bench_dumbbell_flyes, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Front Shoulder Dumbbell Raises", R.drawable.front_shoulder_dumbbell_raises, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Lateral Shoulder Dumbbell Raises", R.drawable.lateral_shoulder_dumbbell_raises, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Lying Dumbbell Tricep Extensions", R.drawable.lying_dumbbell_tricep_extensions, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Single Arm Dumbbell Bench Rows", R.drawable.single_arm_dumbbell_bench_rows, true, true,false,false));
//        exerciseItemList.add(new ExerciseItem("Standing Dumbbell Calf Raises", R.drawable.standing_dumbbell_calf_raises, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Upright Dumbbell Rows", R.drawable.upright_dumbbell_rows, true, true,false,false));
        return exerciseItemList;
    }
    private static List<ExerciseItem> getMachineItemList(){
        List<ExerciseItem> exerciseItemList = new ArrayList<>();
        exerciseItemList.add(new ExerciseItem("Cable Core Rotation", R.drawable.cable_core_rotation, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Cable Hammer Bicep Curls", R.drawable.cable_hammer_bicep_curls, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Cable Rope Overhead Triceps Extensions", R.drawable.cable_rope_overhead_triceps_extensions, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Close Grip Lat Pulldowns", R.drawable.lat_pulldowns, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Seated Chest Press", R.drawable.seated_chest_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Leg Press", R.drawable.leg_press, true, true,false,false));
        exerciseItemList.add(new ExerciseItem("Machine Bicep Preacher ", R.drawable.machine_bicep_preacher, true, true,false,false));
//        exerciseItemList.add(new ExerciseItem("Ab Crunch Machine", R.drawable.ab_crunch_machine, true, true,false,false));
//        exerciseItemList.add(new ExerciseItem("Adduction Inner Thigh Machine", R.drawable.adduction_inner_thigh_machine, true, true,false,false));
        return exerciseItemList;
    }
    private static List<ExerciseItem> getSelfWeightItemList(){
        List<ExerciseItem> exerciseItemList = new ArrayList<>();
        exerciseItemList.add(new ExerciseItem("Tricep Dips", R.drawable.tricep_dips, false, true,false,false));
        exerciseItemList.add(new ExerciseItem("Elbow-to-Knee Crunches", R.drawable.elbow_to_knee_crunches, false, true,false,false));
        exerciseItemList.add(new ExerciseItem("Chinups", R.drawable.chinups, false, true,false,false));
        exerciseItemList.add(new ExerciseItem("Bird Dogs", R.drawable.bird_dogs, false, true,false,false));
//        exerciseItemList.add(new ExerciseItem("Bodyweight Shoulder Presses", R.drawable.bodyweight_shoulder_presses, false, true,false,false));
        exerciseItemList.add(new ExerciseItem("Back Extensions", R.drawable.back_extensions, false, true,false,false));
        exerciseItemList.add(new ExerciseItem("Crunches", R.drawable.crunches, false, true,false,false));
        exerciseItemList.add(new ExerciseItem("Pull-ups", R.drawable.pull_ups, false, true,false,false));
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
