package com.example.fitnesslog.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitnesslog.databinding.DialogNewRoutineLayoutBinding;
import com.example.fitnesslog.databinding.FragmentNavigationAllWorkoutsBinding;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.view.activities.MainActivity;
import com.example.fitnesslog.view.adapters.RoutineAdapter;
import com.example.fitnesslog.viewmodel.RoutineViewModel;
import com.example.fitnesslog.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllWorkoutFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllWorkoutFragment extends Fragment implements
        View.OnClickListener {


    private FragmentNavigationAllWorkoutsBinding mBinding;
    private RoutineViewModel mRoutineViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRoutineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentNavigationAllWorkoutsBinding.inflate(inflater,container,false);
        mBinding.fabAddRoutine.setOnClickListener(this);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.rvRoutinesList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        RoutineAdapter mRoutineAdapter = new RoutineAdapter(this);
        mBinding.rvRoutinesList.setAdapter(mRoutineAdapter);
        mRoutineViewModel.getAllRoutine().observe(getViewLifecycleOwner(), mRoutineAdapter::setRoutines);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (requireActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showBottomNavigationView();
        }
    }

    public void workoutFragment(int routineId){
        if (requireActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).hideBottomNavigationView();
        }
        Toast.makeText(getContext(),"go to routine", Toast.LENGTH_SHORT).show();
        NavController navController = NavHostFragment.findNavController(this);
        NavDirections action = AllWorkoutFragmentDirections.actionNavigationAllWorkoutsToWorkoutFragment(routineId);
        navController.navigate(action);
    }

    public void deleteRoutine(Routine routine){
        mRoutineViewModel.deleteRoutine(routine);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_add_routine:
                Dialog dialog = new Dialog(getActivity());
                DialogNewRoutineLayoutBinding dBinding = DialogNewRoutineLayoutBinding.inflate(getLayoutInflater());
                dBinding.chipColorPicker.check(dBinding.chipColorRed.getId());
                dBinding.chipTypePicker.check(dBinding.chipTypeMonday.getId());
                dBinding.btnRoutineCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String routineName = dBinding.evRoutineName.getEditText().getText().toString();
                        String routineType =((Chip) dBinding.getRoot().findViewById(dBinding.chipTypePicker.getCheckedChipId())).getText().toString() ;
                        Integer routineColor = ((Chip) dBinding.getRoot().findViewById(dBinding.chipColorPicker.getCheckedChipId())).getChipBackgroundColor().getDefaultColor();
                        if (routineName != ""){
                            Routine routine = new Routine(routineName,routineType,routineColor);
                            long routineId = 0;
                            try {
                                routineId = mRoutineViewModel.insertRoutine(routine);
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getContext() ,"You have created a new routine :" + routineId,Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.setTitle("Add new Routine");
                dialog.setContentView(dBinding.getRoot());
                dialog.getWindow().setLayout(1000, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                break;
            default:
                break;
        }
    }
}