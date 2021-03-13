package com.example.fitnesslog.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    public int exerciseId;

    public int parentRoutineId;

    public String name;

    public boolean isCardio;

    public int lastTime;

    public int setNumber;
    
    public float resistance;
    
    public int speed;
    
    public int repOrDuration;

    public Exercise(String name, boolean isCardio, int parentRoutineId){
        this.parentRoutineId = parentRoutineId;
        this.name = name;
        this.isCardio = isCardio;
        this.lastTime = 0;
        this.setNumber = 0;
        this.resistance = 0;
        this.speed = 0;
        this.repOrDuration = 8;
    }

}
