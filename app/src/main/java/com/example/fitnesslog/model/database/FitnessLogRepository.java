package com.example.fitnesslog.model.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.fitnesslog.model.entities.Body;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.model.pojo.RoutineWithExercise;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FitnessLogRepository {
    private FitnessLogDao mFitnessLogDao;
    private LiveData<List<RoutineWithExercise>> mAllRoutines;


    public FitnessLogRepository(Application application) {
        FitnessLogDatabase db = FitnessLogDatabase.getDatabase(application);
        mFitnessLogDao = db.fitnessLogDao();
        mAllRoutines = mFitnessLogDao.getAllRoutine();
    }

    public LiveData<List<RoutineWithExercise>> getAllRoutines(){
        return mAllRoutines;
    }
    public LiveData<List<Body>> getAllBody(){
        return  mFitnessLogDao.getAllBody();
    }
    public LiveData<List<Body>> getRecentBody(){
        return  mFitnessLogDao.getRecentBody();
    }
    public LiveData<List<Exercise>> getAllExercise(){
        return mFitnessLogDao.getAllExercise();
    }
    public LiveData<RoutineWithExercise> getRoutineDetail(int routineId){
        return mFitnessLogDao.getRoutineWithExercise(routineId);
    }

    public long insertRoutine(Routine routine) throws ExecutionException, InterruptedException {
        Future<Long> future = FitnessLogDatabase.databaseWriteExecutor.submit(() -> {
            return mFitnessLogDao.insertRoutine(routine);
        });
        return future.get();
    }

    public void deleteRoutine(Routine routine){
        FitnessLogDatabase
                .databaseWriteExecutor.execute(() -> {
            mFitnessLogDao.deleteRoutine(routine);
        });
    }
    public void deleteExercise(Exercise exercise){
        FitnessLogDatabase
                .databaseWriteExecutor.execute(() -> {
            mFitnessLogDao.deleteExercise(exercise);
        });
    }
    public void insertExercise(Exercise exercise){
        FitnessLogDatabase
                .databaseWriteExecutor.execute(() -> {
            mFitnessLogDao.insertExercise(exercise);
        });
    }
    public void insertBody(Body body){
        FitnessLogDatabase
                .databaseWriteExecutor.execute(() -> {
            mFitnessLogDao.insertBody(body);
        });
    }
    public void updateExercise(Exercise exercise){
        FitnessLogDatabase
                .databaseWriteExecutor.execute(() -> {
            mFitnessLogDao.updateExercise(exercise);
        });
    }
    public void updateRoutine(Routine routine){
        FitnessLogDatabase
                .databaseWriteExecutor.execute(() -> {
            mFitnessLogDao.updateRoutine(routine);
        });
    }
}
