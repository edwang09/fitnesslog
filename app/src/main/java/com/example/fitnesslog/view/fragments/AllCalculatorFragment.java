package com.example.fitnesslog.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnesslog.databinding.FragmentNavigationAllCalculatorsBinding;
import com.example.fitnesslog.view.activities.MainActivity;

public class AllCalculatorFragment extends Fragment implements View.OnClickListener {
    private FragmentNavigationAllCalculatorsBinding mBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentNavigationAllCalculatorsBinding.inflate(inflater,container,false);
        mBinding.cvBmiCalculator.setOnClickListener(this);
        mBinding.cvFatCalculator.setOnClickListener(this);
        mBinding.cv1rmCalculator.setOnClickListener(this);
        return mBinding.getRoot();

    }


    public void bmiCalculator(){
        if (requireActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).hideBottomNavigationView();
        }
        NavController navController = NavHostFragment.findNavController(this);
        NavDirections action = AllCalculatorFragmentDirections.actionNavigationAllCalculatorsToBmiCalculator();
        navController.navigate(action);
    }
    public void fatCalculator(){
        if (requireActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).hideBottomNavigationView();
        }
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(AllCalculatorFragmentDirections.actionNavigationAllCalculatorsToFatCalculatorFragment());
    }
    public void oneRmCalculator(){
        if (requireActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).hideBottomNavigationView();
        }
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(AllCalculatorFragmentDirections.actionNavigationAllCalculatorsToOneRmCalculatorFragment());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mBinding.cvBmiCalculator.getId() ){
            bmiCalculator();
        }else if(v.getId() == mBinding.cvFatCalculator.getId()) {
            fatCalculator();
        }else if(v.getId() == mBinding.cv1rmCalculator.getId()) {
            oneRmCalculator();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (requireActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showBottomNavigationView();
        }
    }
}