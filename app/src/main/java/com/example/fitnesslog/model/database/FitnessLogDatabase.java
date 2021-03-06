package com.example.fitnesslog.model.database;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fitnesslog.R;
import com.example.fitnesslog.model.entities.Body;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Routine.class, Exercise.class, Body.class}, version = 10, exportSchema = false)
public abstract class FitnessLogDatabase extends RoomDatabase {
    public abstract FitnessLogDao fitnessLogDao();

    private static volatile FitnessLogDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static FitnessLogDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FitnessLogDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FitnessLogDatabase.class, "fitness_log_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                FitnessLogDao dao = INSTANCE.fitnessLogDao();
                dao.deleteAllRoutine();
                dao.deleteAllExercise();
                Routine routine = new Routine(
                    "test routine", "MON",
                        Color.parseColor("#4287f5")
                );
                Exercise exercise = new Exercise("Bench Press", routine.routineId, R.drawable.barbell_bench_press,true, true, false,false);
                Log.i("Database","Call back run");
                dao.insertRoutine(routine);
                dao.insertExercise(exercise);
            });
        }
    };


}
