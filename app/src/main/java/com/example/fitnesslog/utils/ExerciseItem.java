package com.example.fitnesslog.utils;

public class ExerciseItem {
    public String name;
    public int imageResource;
    public boolean hasResistance;
    public boolean hasSpeed;
    public boolean hasDuration;
    public boolean hasRep;
    public ExerciseItem(String name, int imageResource, boolean hasResistance, boolean hasRep , boolean hasSpeed, boolean hasDuration){
        this.name = name;
        this.hasResistance = hasResistance;
        this.hasSpeed = hasSpeed;
        this.hasDuration = hasDuration;
        this.hasRep = hasRep;
        this.imageResource = imageResource;
    }
}
