package com.example.fitnesslog.model.pojo;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;

import java.util.List;

public class RoutineWithExercise {
    @Embedded
    public Routine routine;


    @Relation(parentColumn = "routineId", entityColumn = "parentRoutineId")
    public List<Exercise> exercises;


}
