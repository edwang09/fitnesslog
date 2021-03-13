package com.example.fitnesslog.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.DialogFatCalculatorInstructionBinding;
import com.example.fitnesslog.databinding.DialogNewRoutineLayoutBinding;
import com.example.fitnesslog.databinding.FragmentBmiCalculatorBinding;
import com.example.fitnesslog.databinding.FragmentFatCalculatorBinding;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.utils.Calculators;
import com.google.android.material.chip.Chip;

import java.util.concurrent.ExecutionException;

public class FatCalculatorFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private FragmentFatCalculatorBinding mBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFatCalculatorBinding.inflate(inflater, container, false);
        mBinding.rgTvFatCalculatorGender.check(mBinding.rvTvFatCalculatorGenderMale.getId());
        mBinding.evFatAge.setHint("Age");
        mBinding.evFatLoc1.setHint("Chest");
        mBinding.evFatLoc2.setHint("Abdominal");
        mBinding.evFatLoc3.setHint("Thigh");
        mBinding.rgTvFatCalculatorGender.setOnCheckedChangeListener(this);

        mBinding.btnCalculate.setOnClickListener(this);
        mBinding.tvBodyFatHow.setOnClickListener(this);
        mBinding.btnClear.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == mBinding.rvTvFatCalculatorGenderMale.getId()){
            mBinding.evFatLoc1.setHint("Chest");
            mBinding.evFatLoc2.setHint("Abdominal");
        }else{
            mBinding.evFatLoc1.setHint("Tricep");
            mBinding.evFatLoc2.setHint("Suprailiac");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mBinding.btnCalculate.getId()){
            boolean isMale = mBinding.rgTvFatCalculatorGender.getCheckedRadioButtonId() == mBinding.rvTvFatCalculatorGenderMale.getId();
            int age = Integer.parseInt(mBinding.evFatAge.getEditText().getText().toString());
            float sumOfSkinfold = Float.parseFloat(mBinding.evFatLoc1.getEditText().getText().toString()) +
                    Float.parseFloat(mBinding.evFatLoc3.getEditText().getText().toString()) +
                    Float.parseFloat(mBinding.evFatLoc2.getEditText().getText().toString());
            float bodyFatPercentage = Calculators.CalculateFat(isMale, sumOfSkinfold, age);
            showFatDialog(bodyFatPercentage);
        }else if(v.getId() == mBinding.btnClear.getId()){
            mBinding.evFatAge.getEditText().setText("");
            mBinding.evFatLoc1.getEditText().setText("");
            mBinding.evFatLoc2.getEditText().setText("");
            mBinding.evFatLoc3.getEditText().setText("");
        }else if( v.getId() == mBinding.tvBodyFatHow.getId()){
            Dialog dialog = new Dialog(getActivity());
            DialogFatCalculatorInstructionBinding dBinding = DialogFatCalculatorInstructionBinding.inflate(getLayoutInflater());
            dialog.setTitle("Instruction");
            dialog.setContentView(dBinding.getRoot());
            dialog.getWindow().setLayout(1000, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();
        }
    }

    public void showFatDialog(float fat){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Body Fat Percentage");
        builder.setIcon(R.drawable.ic_heart);
        builder.setMessage("Your Body Fat Percentage is " + String.format("%.1f", fat));
        builder.setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
