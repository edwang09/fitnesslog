package com.example.fitnesslog.view.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitnesslog.databinding.DialogBodyInputBinding;
import com.example.fitnesslog.databinding.FragmentNavigationAllBodiesBinding;
import com.example.fitnesslog.model.entities.Body;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.view.activities.MainActivity;
import com.example.fitnesslog.view.adapters.BodyTabAdapter;
import com.example.fitnesslog.viewmodel.RoutineViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllBodyFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllBodyFragment extends Fragment {


    private FragmentNavigationAllBodiesBinding mBinding;
    private RoutineViewModel mRoutineViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRoutineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentNavigationAllBodiesBinding.inflate(inflater,container,false);
        mRoutineViewModel.getRecentBody().observe(getViewLifecycleOwner(),this::setBody);

        mBinding.fabBodyFatMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float fat = Float.parseFloat(mBinding.tvBodyFat.getText().toString());
                fat-=0.1;
                mBinding.tvBodyFat.setText(String.format(Locale.US,"%.1f",fat));
            }
        });
        mBinding.fabBodyFatPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float fat = Float.parseFloat(mBinding.tvBodyFat.getText().toString());
                fat+=0.1;
                mBinding.tvBodyFat.setText(String.format(Locale.US,"%.1f",fat));
            }
        });

        mBinding.fabBodyWeightMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight = Float.parseFloat(mBinding.tvBodyWeight.getText().toString());
                weight-=0.1;
                mBinding.tvBodyWeight.setText(String.format(Locale.US,"%.1f",weight));
            }
        });
        mBinding.fabBodyWeightPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight = Float.parseFloat(mBinding.tvBodyWeight.getText().toString());
                weight+=0.1;
                mBinding.tvBodyWeight.setText(String.format(Locale.US,"%.1f",weight));
            }
        });


        mBinding.fabBodySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight = Float.parseFloat(mBinding.tvBodyWeight.getText().toString());
                float fat = Float.parseFloat(mBinding.tvBodyFat.getText().toString());
                Body body = new Body(weight,fat,TimeUnit.MILLISECONDS.toDays((new Date()).getTime()));
                mRoutineViewModel.insertBody(body);
                Toast.makeText(getContext(),"Well done! You have recorded successfully.", Toast.LENGTH_LONG).show();
            }
        });

        mBinding.tvBodyFat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                DialogBodyInputBinding dBinding = DialogBodyInputBinding.inflate(getLayoutInflater());
                dBinding.outlinedTextField.setHint("Body Fat %");
                dBinding.outlinedTextField.setSuffixText("%");
                dBinding.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        float fat = Float.parseFloat(dBinding.outlinedTextField.getEditText().getText().toString());
//                        String input = dBinding.outlinedTextField.getEditText().getText().toString();
                        mBinding.tvBodyFat.setText(String.format(Locale.US, "%.1f", fat));
                        Toast.makeText(getContext() ,"You have edited your fat",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                dialog.setTitle("Fat %");
                dialog.setContentView(dBinding.getRoot());
                dialog.show();
            }
        });
        mBinding.tvBodyWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                DialogBodyInputBinding dBinding = DialogBodyInputBinding.inflate(getLayoutInflater());
                dBinding.outlinedTextField.setHint("Body Weight");
                dBinding.outlinedTextField.setSuffixText("lbs");
                dBinding.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        float weight = Float.parseFloat(dBinding.outlinedTextField.getEditText().getText().toString());
                        mBinding.tvBodyWeight.setText(String.format(Locale.US, "%.1f", weight));
                        Toast.makeText(getContext() ,"You have edited your weight",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                dialog.setTitle("Weight");
                dialog.setContentView(dBinding.getRoot());
                dialog.show();
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BodyTabAdapter bodyTabAdapter = new BodyTabAdapter(getChildFragmentManager(), 0);
        mBinding.vpBody.setAdapter(bodyTabAdapter);
    }

    public void setBody(List<Body> bodies){
//        buildChart(bodies);
        if (bodies != null && bodies.size() >= 1){
            mBinding.tvBodyWeight.setText(String.valueOf(bodies.get(0).weight));
            mBinding.tvBodyFat.setText(String.valueOf(bodies.get(0).fat));
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