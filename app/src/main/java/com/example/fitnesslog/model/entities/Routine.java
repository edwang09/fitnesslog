package com.example.fitnesslog.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "routines_table")
public class Routine {
    @PrimaryKey(autoGenerate = true)
    public int routineId;

    public long lastTrained;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "color")
    public int color;

    public Routine(@NonNull String name,@NonNull String type,@NonNull int color) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.lastTrained = 0;
    }
    public Routine() {
        this.name = "";
        this.type = "Mon";
        this.color = 16339281;
    }
}
