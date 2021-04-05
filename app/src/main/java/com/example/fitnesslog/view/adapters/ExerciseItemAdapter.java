package com.example.fitnesslog.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesslog.databinding.ItemExerciseItemBinding;
import com.example.fitnesslog.databinding.ItemExerciseLayoutBinding;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.utils.Constants;
import com.example.fitnesslog.utils.ExerciseItem;
import com.example.fitnesslog.view.fragments.SelectExerciseFragment;
import com.example.fitnesslog.view.fragments.SelectExerciseTabFragment;
import com.example.fitnesslog.view.fragments.WorkoutFragment;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

public class ExerciseItemAdapter extends RecyclerView.Adapter<ExerciseItemAdapter.ViewHolder> {
    private SelectExerciseTabFragment fragment;
    private Context context;
    private List<ExerciseItem> mExerciseItems;
    private Routine mRoutine;
    private String tabName;


    public ExerciseItemAdapter(SelectExerciseTabFragment fragment, String tabName){
        this.fragment = fragment;
        this.tabName = tabName;
        this.context = fragment.getContext();
        mExerciseItems = Constants.getExerciseItemList(tabName);
    }
    @NonNull
    @Override
    public ExerciseItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExerciseItemBinding binding = ItemExerciseItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ExerciseItemAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvExerciseItem.setText(mExerciseItems.get(position).name);
        holder.ivExerciseImage.setImageResource(mExerciseItems.get(position).imageResource);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.selectExercise(mExerciseItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExerciseItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivExerciseImage;
        private final TextView tvExerciseItem;
        public ViewHolder(@NonNull ItemExerciseItemBinding itemView ) {
            super(itemView.getRoot());
            this.tvExerciseItem = itemView.tvExerciseItem;
            this.ivExerciseImage = itemView.ivExerciseImage;

        }
    }
}
