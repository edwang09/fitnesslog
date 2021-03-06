package com.example.fitnesslog.view.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.DialogNewExerciseLayoutBinding;
import com.example.fitnesslog.databinding.DialogNewRoutineLayoutBinding;
import com.example.fitnesslog.databinding.FragmentWorkoutBinding;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.model.pojo.RoutineWithExercise;
import com.example.fitnesslog.view.adapters.ExerciseAdapter;
import com.example.fitnesslog.viewmodel.RoutineViewModel;
import com.google.android.material.chip.Chip;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutFragment extends Fragment implements
        View.OnClickListener {

    private FragmentWorkoutBinding mBinding;
    private RoutineViewModel mRoutineViewModel;
    private int routineId;
    private ExerciseAdapter mExerciseAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mRoutineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //view binding
        mBinding = FragmentWorkoutBinding.inflate(getLayoutInflater());
        mBinding.fabAddExercise.setOnClickListener(this);
        mBinding.fabEndExercise.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        routineId = WorkoutFragmentArgs.fromBundle(getArguments()).getRoutineId();

        mBinding.rvExerciseList.setLayoutManager(new LinearLayoutManager(requireContext()));
        mExerciseAdapter = new ExerciseAdapter(this);
        mBinding.rvExerciseList.setAdapter(mExerciseAdapter);
        mRoutineViewModel.getRoutineDetail(routineId).observe(getViewLifecycleOwner(), mExerciseAdapter::setExercises);
    }
    public void switchNoExerciseHolder(boolean on){
        if (on){
            mBinding.svWorkoutShowWorkout.setVisibility(View.GONE);
            mBinding.tvWorkoutNoWorkout.setVisibility(View.VISIBLE);
        }else{
            mBinding.svWorkoutShowWorkout.setVisibility(View.VISIBLE);
            mBinding.tvWorkoutNoWorkout.setVisibility(View.GONE);
        }
    }
    public void updateExercise(Exercise exercise){
        mRoutineViewModel.updateExercise(exercise);
    }
    public void updateRoutine(Routine routine){
        mRoutineViewModel.updateRoutine(routine);
    }

    public void deleteExercise(Exercise exercise){
        mRoutineViewModel.deleteExercise(exercise);
    }


    @Override
    public void onClick(View v) {
        NavController navController = NavHostFragment.findNavController(this);
        switch (v.getId()){
            case R.id.fab_add_exercise:

                NavDirections action = WorkoutFragmentDirections.actionWorkoutFragmentToSelectExerciseFragment(routineId);
                navController.navigate(action);
                break;
            case R.id.fab_end_exercise:
                Log.i("Workout Fragment","Finish workout");
                mExerciseAdapter.completeWorkout();
                navController.popBackStack();
                break;
            default:
                break;
        }
    }
}