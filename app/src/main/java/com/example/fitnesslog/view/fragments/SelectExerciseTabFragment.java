package com.example.fitnesslog.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fitnesslog.databinding.FragmentSelectExerciseBinding;
import com.example.fitnesslog.databinding.FragmentSelectExerciseTabBinding;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.utils.ExerciseItem;
import com.example.fitnesslog.view.adapters.ExerciseItemAdapter;
import com.example.fitnesslog.view.adapters.ExerciseItemTabAdapter;
import com.example.fitnesslog.viewmodel.RoutineViewModel;

public class SelectExerciseTabFragment extends Fragment {

    private FragmentSelectExerciseTabBinding mBinding;
    SelectExerciseFragment fragment;
    private String tabName;

    public SelectExerciseTabFragment(String tabName, SelectExerciseFragment fragment){
        this.tabName = tabName;
        this.fragment = fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //view binding
        mBinding = FragmentSelectExerciseTabBinding.inflate(getLayoutInflater());

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.rvExerciseList.setLayoutManager(new LinearLayoutManager(requireContext()));
        ExerciseItemAdapter mExerciseItemAdapter = new ExerciseItemAdapter(this, tabName);
        mBinding.rvExerciseList.setAdapter(mExerciseItemAdapter);


    }
    public void selectExercise(ExerciseItem exerciseItem){
        this.fragment.selectExercise(exerciseItem);

    }
}