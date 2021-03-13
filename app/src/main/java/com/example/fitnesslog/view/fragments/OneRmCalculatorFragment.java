package com.example.fitnesslog.view.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.Fragment1rmCalculatorBinding;
import com.example.fitnesslog.databinding.FragmentFatCalculatorBinding;
import com.example.fitnesslog.utils.Calculators;
import com.google.android.material.slider.Slider;

public class OneRmCalculatorFragment  extends Fragment implements View.OnClickListener, Slider.OnSliderTouchListener {

    private Fragment1rmCalculatorBinding mBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = Fragment1rmCalculatorBinding.inflate(inflater, container, false);

        mBinding.svRepOrDurationSlider.addOnSliderTouchListener(this);


        mBinding.btnCalculate.setOnClickListener(this);
        mBinding.btnClear.setOnClickListener(this);
        return mBinding.getRoot();
    }




    @Override
    public void onClick(View v) {
        if (v.getId() == mBinding.btnCalculate.getId()) {
            int repetition = (int) mBinding.svRepOrDurationSlider.getValue();
            float lift = Float.parseFloat(mBinding.tvLift.getEditText().getText().toString());
            float oneRm = Calculators.Calculate1Rm(lift, repetition);
            show1RmDialog(oneRm);

        } else if (v.getId() == mBinding.btnClear.getId()) {
            mBinding.tvLift.getEditText().setText("");
            mBinding.svRepOrDurationSlider.setValue(1f);
        }
    }
    public void show1RmDialog(float oneRm){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("1 Rep Maximum");
        builder.setIcon(R.drawable.ic_heart);
        builder.setMessage("Your 1 Rep Maximum is " + String.format("%.1f", oneRm));
        builder.setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onStartTrackingTouch(@NonNull Slider slider) {

    }

    @Override
    public void onStopTrackingTouch(@NonNull Slider slider) {
        mBinding.tv1rmCalculatorRepetition.setText("Repetition: " + String.valueOf((int) slider.getValue()));
    }
}
