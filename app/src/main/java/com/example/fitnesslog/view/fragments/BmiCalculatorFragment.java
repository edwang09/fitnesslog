package com.example.fitnesslog.view.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.FragmentBmiCalculatorBinding;
import com.example.fitnesslog.databinding.FragmentNavigationAllCalculatorsBinding;
import com.example.fitnesslog.utils.Calculators;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BmiCalculatorFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class BmiCalculatorFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private FragmentBmiCalculatorBinding mBinding;
    private Boolean useMetric = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentBmiCalculatorBinding.inflate(inflater, container, false);
        mBinding.tiHeightCm.setVisibility(View.GONE);
        mBinding.smSwitch.setOnCheckedChangeListener(this);
        mBinding.btnCalculate.setOnClickListener(this);
        mBinding.btnClear.setOnClickListener(this);




        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_calculate:
                Float weight = Float.parseFloat(mBinding.tvWeight.getEditText().getText().toString());
                Float height;
                if (useMetric){
                    height = Float.parseFloat(mBinding.tiHeightCm.getEditText().getText().toString());
                }else{
                    height = Float.parseFloat(mBinding.tiHeightFoot.getEditText().getText().toString()) * 12f +
                            Float.parseFloat(mBinding.tiHeightInch.getEditText().getText().toString());
                }
                Float bmi = Calculators.CalculateBMI(weight, height, useMetric);
                String explanation = Calculators.GetBmiExplanation(bmi);
                Log.i("BMI",bmi.toString());
                Log.i("BMI Explanation",explanation);
                showBMIDialog(bmi, explanation);

                break;
            case R.id.btn_clear:
                mBinding.tvWeight.getEditText().setText("");
                mBinding.tiHeightCm.getEditText().setText("");
                mBinding.tiHeightFoot.getEditText().setText("");
                mBinding.tiHeightInch.getEditText().setText("");

                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.sm_switch:
                if (isChecked){
                    mBinding.lvStandard.setVisibility(View.GONE);
                    mBinding.tiHeightCm.setVisibility(View.VISIBLE);
                    mBinding.tvWeight.setSuffixText("kg");
                    useMetric = true;
                }else{
                    mBinding.lvStandard.setVisibility(View.VISIBLE);
                    mBinding.tiHeightCm.setVisibility(View.GONE);
                    mBinding.tvWeight.setSuffixText("lbs");
                    useMetric = false;
                }

                break;
            default:
                break;
        }
    }
    public void showBMIDialog(Float bmi, String explanation){
        this.getView().clearFocus();
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Your BMI is " + String.format("%.1f", bmi));
        builder.setIcon(R.drawable.ic_heart);
        builder.setMessage(explanation);
        builder.setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        }

}