package com.example.fitnesslog.view.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fitnesslog.view.fragments.BodyChartFatFragment;
import com.example.fitnesslog.view.fragments.BodyChartWeightFragment;

public class BodyTabAdapter extends FragmentPagerAdapter {
    public BodyTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new BodyChartWeightFragment();
        }else if(position == 1){
            return new BodyChartFatFragment();
        }
        return new BodyChartWeightFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "Weight";
        }else if(position == 1){
            return "Body Fat";
        }
        return "";
    }

}
