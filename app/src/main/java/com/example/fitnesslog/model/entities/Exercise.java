package com.example.fitnesslog.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public int exerciseId;
    public int parentRoutineId;
    public String name;
    public boolean hasResistance;
    public boolean hasSpeed;
    public boolean hasDuration;
    public boolean hasRep;
    public int lastTime;
    public int setNumber;
    public float resistance;
    public float speed;
    public int rep;
    public int duration;
    public int image;
    public Exercise(String name, int parentRoutineId, int image,boolean hasResistance, boolean hasSpeed,boolean hasDuration,boolean hasRep){
        this.parentRoutineId = parentRoutineId;
        this.name = name;
        this.image = image;
        this.hasResistance = hasResistance;
        this.hasSpeed = hasSpeed;
        this.hasDuration = hasDuration;
        this.hasRep = hasRep;

        this.lastTime = 0;
        this.setNumber = 0;
        this.resistance = 20;
        this.speed = (float) 5.0;
        this.rep = 8;
        this.duration = 20;
    }

}
