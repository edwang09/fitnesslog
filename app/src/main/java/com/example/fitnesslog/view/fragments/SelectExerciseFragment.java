package com.example.fitnesslog.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.FragmentSelectExerciseBinding;
import com.example.fitnesslog.databinding.FragmentWorkoutBinding;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.utils.ExerciseItem;
import com.example.fitnesslog.view.adapters.BodyTabAdapter;
import com.example.fitnesslog.view.adapters.ExerciseAdapter;
import com.example.fitnesslog.view.adapters.ExerciseItemAdapter;
import com.example.fitnesslog.view.adapters.ExerciseItemTabAdapter;
import com.example.fitnesslog.viewmodel.RoutineViewModel;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectExerciseFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectExerciseFragment extends Fragment {

    private FragmentSelectExerciseBinding mBinding;
    private RoutineViewModel mRoutineViewModel;
    private int routineId;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mRoutineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //view binding
        mBinding = FragmentSelectExerciseBinding.inflate(getLayoutInflater());

        //pager adapter
        ExerciseItemTabAdapter exerciseItemTabAdapter = new ExerciseItemTabAdapter(getChildFragmentManager(),0, this);
        mBinding.VPExerciseItemSelect.setAdapter(exerciseItemTabAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        routineId = SelectExerciseFragmentArgs.fromBundle(getArguments()).getRoutineId();

    }
    public void selectExercise(ExerciseItem exerciseItem){
        Exercise exercise = new Exercise(exerciseItem.name, routineId, exerciseItem.imageResource, exerciseItem.hasResistance,exerciseItem.hasSpeed, exerciseItem.hasDuration, exerciseItem.hasRep );
        mRoutineViewModel.insertExercise(exercise);
        NavController navController = NavHostFragment.findNavController(this);
        navController.popBackStack();

    }
}