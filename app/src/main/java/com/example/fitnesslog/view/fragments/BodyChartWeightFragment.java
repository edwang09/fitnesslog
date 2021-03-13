package com.example.fitnesslog.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnesslog.databinding.FragmentBodyChartWeightBinding;
import com.example.fitnesslog.model.entities.Body;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BodyChartWeightFragment extends Fragment {

    private FragmentBodyChartWeightBinding mBinding;
    private RoutineViewModel mRoutineViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentBodyChartWeightBinding.inflate(inflater, container, false);

        mRoutineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        mRoutineViewModel.getRecentBody().observe(getViewLifecycleOwner(),this::buildChart);

        return mBinding.getRoot();
    }

    public void buildChart(List<Body> bodies){

        List<Entry> entries = new ArrayList<>();
        for (Body body : bodies) {
            // turn your data into Entry objects
            Log.i("Weight Chart",String.valueOf(body.timeStamp));
            entries.add(new Entry(body.timeStamp, body.weight));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Weight");
        LineData lineData = new LineData(dataSet);


        LineChart lineChart = mBinding.lcBodyChart;
        XAxis xAxis =lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter(){
            private final SimpleDateFormat mFormat = new SimpleDateFormat("MM-dd", Locale.ENGLISH);
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return mFormat.format(new Date(TimeUnit.DAYS.toMillis((long) value)));
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
//        xAxis.setTypeface();
        xAxis.setTextSize(12f);
//        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMaximum(TimeUnit.MILLISECONDS.toDays(new Date().getTime()) + 1);
        xAxis.setAxisMinimum((TimeUnit.MILLISECONDS.toDays(new Date().getTime()) - 7));
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.rgb(255, 192, 56));
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextSize(12f);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
//        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setAxisMinimum(50f);
        leftAxis.setAxisMaximum(200f);
        leftAxis.setYOffset(-9f);
        leftAxis.setTextSize(15f);
        leftAxis.setTextColor(Color.rgb(255, 192, 56));

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
        leftAxis.setEnabled(false);

        lineData.setValueTextSize(15f);
        lineData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format("%.1f",value);
            }
        });

//        Description d = new Description();
//        d.setText("");
//        lineChart.setDescription(d);

        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }


}
