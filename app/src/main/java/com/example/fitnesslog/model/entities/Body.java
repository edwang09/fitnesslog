package com.example.fitnesslog.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.concurrent.TimeUnit;

@Entity(tableName = "body_table")
public class Body {

    @PrimaryKey
    @ColumnInfo(name = "time_stamp")
    public long timeStamp;

    public float weight;

    public float fat;


    public Body(@NonNull float weight, @NonNull float fat, @NonNull long timeStamp) {
        this.weight = weight;
        this.fat = fat;
        this.timeStamp = timeStamp;
    }

}
