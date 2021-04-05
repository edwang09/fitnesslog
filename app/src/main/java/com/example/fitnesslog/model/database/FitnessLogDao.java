package com.example.fitnesslog.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.fitnesslog.model.entities.Body;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.model.pojo.RoutineWithExercise;

import java.util.List;

@Dao
public interface FitnessLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRoutine(Routine routine);
    @Insert
    void insertExercise(Exercise exercise);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBody(Body body);

    @Update
    void updateExercise(Exercise exercise);
    @Update
    void updateRoutine(Routine routine);

    @Delete
    void deleteRoutine(Routine routine);
    @Delete
    void deleteExercise(Exercise exercise);

    @Query("DELETE FROM routines_table")
    void deleteAllRoutine();

    @Query("DELETE FROM exercise_table")
    void deleteAllExercise();

    @Query("SELECT * FROM routines_table")
    LiveData<List<RoutineWithExercise>> getAllRoutine();

    @Query("SELECT * FROM body_table")
    LiveData<List<Body>> getAllBody();


    @Query("SELECT * FROM body_table ORDER BY time_stamp DESC LIMIT 10")
    LiveData<List<Body>> getRecentBody();

    @Query("SELECT * FROM exercise_table")
    LiveData<List<Exercise>> getAllExercise();

    @Transaction
    @Query("SELECT * FROM routines_table WHERE routineId = :routineId")
    LiveData<RoutineWithExercise> getRoutineWithExercise(int routineId);

}
