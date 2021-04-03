package com.example.fitnesslog.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
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

        //nav controller
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
}