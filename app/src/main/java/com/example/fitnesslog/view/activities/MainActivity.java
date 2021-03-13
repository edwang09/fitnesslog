package com.example.fitnesslog.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.ActivityMainBinding;
import com.example.fitnesslog.databinding.DialogNewRoutineLayoutBinding;
import com.example.fitnesslog.view.fragments.NewRoutineDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements NewRoutineDialogFragment.NoticeDialogListener {
    private ActivityMainBinding mBinding;
    private NavController mNavController;
    private AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //view binding
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);


        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_all_workouts,
                R.id.navigation_all_bodies,
                R.id.navigation_all_calculators)
                .build();
        NavigationUI.setupActionBarWithNavController(this, mNavController, appBarConfiguration);
        NavigationUI.setupWithNavController(mBinding.navView, mNavController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController,appBarConfiguration);
    }

    public void hideBottomNavigationView() {
        mBinding.navView.clearAnimation();
        mBinding.navView.animate().translationY(mBinding.navView.getHeight()).setDuration(300);
        mBinding.navView.setVisibility(View.GONE);
    }
    public void showBottomNavigationView() {
        mBinding.navView.clearAnimation();
        mBinding.navView.animate().translationY(0f).setDuration(300);
        mBinding.navView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        DialogNewRoutineLayoutBinding dBinding = DialogNewRoutineLayoutBinding.inflate(getLayoutInflater(), null,false);
        String str = dBinding.evRoutineName.getEditText().getText().toString();
        String str2 = ((TextInputLayout) getLayoutInflater().inflate(R.layout.dialog_new_routine_layout,null ).getRootView().findViewById(R.id.ev_routine_name)).getEditText().getText().toString();
        Toast.makeText(getApplicationContext(),str2,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}