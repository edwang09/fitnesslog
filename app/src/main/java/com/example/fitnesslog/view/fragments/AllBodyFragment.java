package com.example.fitnesslog.view.fragments;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.DialogBodyAlarmBinding;
import com.example.fitnesslog.databinding.DialogBodyInputBinding;
import com.example.fitnesslog.databinding.FragmentNavigationAllBodiesBinding;
import com.example.fitnesslog.application.BodyNotificationWorker;
import com.example.fitnesslog.model.entities.Body;
import com.example.fitnesslog.view.activities.MainActivity;
import com.example.fitnesslog.view.adapters.BodyTabAdapter;
import com.example.fitnesslog.viewmodel.RoutineViewModel;
import com.google.android.material.timepicker.MaterialTimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllBodyFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllBodyFragment extends Fragment implements  View.OnClickListener{
    private FragmentNavigationAllBodiesBinding mBinding;
    private RoutineViewModel mRoutineViewModel;
    private  AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize view model
        mRoutineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        //set option menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //handle view binding
        mBinding = FragmentNavigationAllBodiesBinding.inflate(inflater,container,false);
        mRoutineViewModel.getRecentBody().observe(getViewLifecycleOwner(),this::setBody);

        //initialize alarm manager for sending notification
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE );

        //button event handling
        mBinding.fabBodyFatMinus.setOnClickListener(this);
        mBinding.fabBodyFatPlus.setOnClickListener(this);
        mBinding.fabBodyWeightMinus.setOnClickListener(this);
        mBinding.fabBodyWeightPlus.setOnClickListener(this);
        mBinding.fabBodySubmit.setOnClickListener(this);
        mBinding.tvBodyFat.setOnClickListener(this);
        mBinding.tvBodyWeight.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //handle tab for charts
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.body_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.body_alarm:
                Dialog dialog = new Dialog(getActivity());
                DialogBodyAlarmBinding dBinding = DialogBodyAlarmBinding.inflate(getLayoutInflater());
                //initialize with shared preference
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                String[] time = sharedPref.getString("time", "09 00 AM").split(" ");
                boolean checked = sharedPref.getBoolean("on", false);
                dBinding.tvBodyAlarmHour.setText(time[0]);
                dBinding.tvBodyAlarmMinute.setText(time[1]);
                dBinding.tvBodyAlarmAmpm.setText(time[2]);
                dBinding.svSwitch.setChecked(checked);

                dBinding.btnPickTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openTimePicker(dBinding);
                    }
                });
                dBinding.lvTimePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openTimePicker(dBinding);
                    }
                });

                dBinding.btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPref.edit()
                                .putString("time", dBinding.tvBodyAlarmHour.getText().toString() + " " + dBinding.tvBodyAlarmMinute.getText().toString() + " " + dBinding.tvBodyAlarmAmpm.getText().toString())
                                .putBoolean("on", dBinding.svSwitch.isChecked())
                                .apply();
                        if (dBinding.svSwitch.isChecked()){
                            int hour = hourToInt(dBinding.tvBodyAlarmHour.getText().toString(), dBinding.tvBodyAlarmAmpm.getText().toString());
                            int minute = minuteToInt(dBinding.tvBodyAlarmMinute.getText().toString());
                            Log.i("Body Fragment","Send Notification at hour : " + hour + ", minute : " + minute);
                            scheduleNotification(getNotification(), hour,minute);
                        }else{
                            Intent notificationIntent = new Intent(getContext(), BodyNotificationWorker.class);
                            pendingIntent = PendingIntent.getBroadcast( getContext(), 0 , notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT ) ;
                            alarmManager.cancel(pendingIntent);
                        }
                        dialog.dismiss();
                    }
                });
                dialog.setTitle("Set Alarm");
                dialog.setContentView(dBinding.getRoot());
                dialog.getWindow().setLayout(1000, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void openTimePicker(DialogBodyAlarmBinding dBinding){
        MaterialTimePicker picker = (new MaterialTimePicker.Builder())
                .setHour(hourToInt(dBinding.tvBodyAlarmHour.getText().toString(), dBinding.tvBodyAlarmAmpm.getText().toString()))
                .setMinute(minuteToInt(dBinding.tvBodyAlarmMinute.getText().toString()))
                .build();
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Body Fragment","Select time at hour : " + picker.getHour() + ", minute : " + picker.getMinute());
                dBinding.tvBodyAlarmHour.setText(String.valueOf(hourToString(picker.getHour())));
                dBinding.tvBodyAlarmMinute.setText(String.valueOf(minuteToString(picker.getMinute())));
                dBinding.tvBodyAlarmAmpm.setText(String.valueOf(hourToAmPm(picker.getHour())));
            }
        });
        picker.show(getParentFragmentManager(), "Choose Alarm Time");
    }

    public void scheduleNotification (Notification notification, int hour, int minute) {
        Intent notificationIntent = new Intent(getContext(), BodyNotificationWorker.class);
        notificationIntent.putExtra(BodyNotificationWorker.NOTIFICATION_ID , 1 );
        notificationIntent.putExtra(BodyNotificationWorker.NOTIFICATION , notification) ;
        pendingIntent = PendingIntent.getBroadcast( getContext(), 0 , notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT ) ;
        assert alarmManager != null;
        Log.i("Body Fragment","Schedule Notification");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        Log.i("Body Fragment",calendar.toString());
        if (calendar.before(Calendar.getInstance()))
            calendar.add(Calendar.HOUR,24);
            Log.i("Body Fragment",calendar.toString());
        Log.i("Body Fragment",String.valueOf(calendar.getTimeInMillis() - (new Date()).getTime()));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() , AlarmManager.INTERVAL_DAY, pendingIntent) ;
    }
    private Notification getNotification () {
        String NOTIFICATION_CHANNEL_ID = "10001" ;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "default");
        builder.setContentTitle( "Body Notification" ) ;
        builder.setContentText("content") ;
        builder.setSmallIcon(R.drawable.ic_heart) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        Log.i("Body Fragment","Build Notification");
        return builder.build() ;
    }
    public String hourToString(int hour){
        hour%=12;
        if (hour == 0) return "12";
        return hour < 10 ? "0" + hour : String.valueOf(hour);
    }

    public String hourToAmPm(int hour){
        hour/=12;
        return hour == 0 ? "AM": "PM";
    }
    public String minuteToString(int minute){
        return minute < 10 ? "0" + minute : String.valueOf(minute);
    }

    public int hourToInt(String hour, String ampm){
        int res = Integer.parseInt(hour)%12;
        if (ampm == "PM") res+=12;
        return res;
    }
    public int minuteToInt(String minute){
        return Integer.parseInt(minute);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== mBinding.fabBodyFatMinus.getId()) {
            mBinding.tvBodyFat.setText(String.format(Locale.US, "%.1f", Float.parseFloat(mBinding.tvBodyFat.getText().toString()) - 0.1));
        }else if(v.getId()== mBinding.fabBodyFatPlus.getId()) {
            mBinding.tvBodyFat.setText(String.format(Locale.US, "%.1f", Float.parseFloat(mBinding.tvBodyFat.getText().toString()) + 0.1));
        }else if(v.getId()== mBinding.fabBodyWeightMinus.getId()) {
            mBinding.tvBodyWeight.setText(String.format(Locale.US,"%.1f",Float.parseFloat(mBinding.tvBodyWeight.getText().toString())-0.1));
        }else if(v.getId()== mBinding.fabBodyWeightPlus.getId()) {
            mBinding.tvBodyWeight.setText(String.format(Locale.US,"%.1f",Float.parseFloat(mBinding.tvBodyWeight.getText().toString())+0.1));
        }else if(v.getId()== mBinding.fabBodySubmit.getId()) {
            float weight = Float.parseFloat(mBinding.tvBodyWeight.getText().toString());
            float fat = Float.parseFloat(mBinding.tvBodyFat.getText().toString());
            Body body = new Body(weight,fat,TimeUnit.MILLISECONDS.toDays((new Date()).getTime()));
            mRoutineViewModel.insertBody(body);
            Toast.makeText(getContext(),"Well done! You have recorded successfully.", Toast.LENGTH_LONG).show();
        }else if(v.getId()== mBinding.tvBodyFat.getId()) {
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
        }else if(v.getId()== mBinding.tvBodyWeight.getId()) {
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
    }
}