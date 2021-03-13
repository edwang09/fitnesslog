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

import com.example.fitnesslog.model.entities.Body;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Routine.class, Exercise.class, Body.class}, version = 8, exportSchema = false)
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

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                FitnessLogDao dao = INSTANCE.fitnessLogDao();
                dao.deleteAllRoutine();
                dao.deleteAllExercise();
                Routine routine = new Routine(
                    "test routine", "MON",
                        Color.parseColor("#4287f5")
                );
                Exercise exercise = new Exercise("Bench Press",true,  routine.routineId);
                Log.i("Database","Call back run");
                dao.insertRoutine(routine);
                dao.insertExercise(exercise);
            });
        }
    };

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE `Exercise` (`uid` INTEGER, "
//                    + "`name` TEXT, PRIMARY KEY(`uid`))");
//        }
//    }

}
