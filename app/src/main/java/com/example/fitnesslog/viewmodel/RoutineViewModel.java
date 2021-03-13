package com.example.fitnesslog.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fitnesslog.model.database.FitnessLogRepository;
import com.example.fitnesslog.model.entities.Body;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.model.pojo.RoutineWithExercise;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoutineViewModel extends AndroidViewModel {
    private FitnessLogRepository mRepository;
    private final LiveData<List<Routine>> mAllRoutine;


    public RoutineViewModel(@NonNull Application application) {
        super(application);
        mRepository = new FitnessLogRepository(application);
        mAllRoutine = mRepository.getAllRoutines();
    }

    public LiveData<List<Routine>> getAllRoutine(){
        return mAllRoutine;
    }
    public LiveData<List<Exercise>> getAllExercise(){
        return mRepository.getAllExercise();
    }
    public LiveData<List<Body>> getAllBody(){
        return mRepository.getAllBody();
    }
    public LiveData<List<Body>> getRecentBody(){
        return mRepository.getRecentBody();
    }
    public LiveData<RoutineWithExercise> getRoutineDetail(int routineId){
        return mRepository.getRoutineDetail(routineId);
    }
    public long insertRoutine(Routine routine) throws ExecutionException, InterruptedException {
        return mRepository.insertRoutine(routine);
    }
    public void deleteRoutine(Routine routine){
        mRepository.deleteRoutine(routine);
    }
    public void deleteExercise(Exercise exercise){
        mRepository.deleteExercise(exercise);
    }
    public void insertExercise(Exercise exercise){
        mRepository.insertExercise(exercise);
    }
    public void insertBody(Body body){
        mRepository.insertBody(body);
    }
    public void updateExercise(Exercise exercise){
        mRepository.updateExercise(exercise);
    }
    public void updateRoutine(Routine routine){
        mRepository.updateRoutine(routine);
    }
}
