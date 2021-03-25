package com.example.fitnesslog.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.ItemExerciseLayoutBinding;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.model.pojo.RoutineWithExercise;
import com.example.fitnesslog.view.fragments.WorkoutFragment;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private WorkoutFragment fragment;
    private Context context;
    private List<Exercise> mExercises;
    private Routine mRoutine;


    public ExerciseAdapter(WorkoutFragment fragment
//            , List<Exercise> exercises
    ){
        this.fragment = fragment;
        this.context = fragment.getContext();
//        this.mExercises = exercises;
        mExercises = new ArrayList<>();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExerciseLayoutBinding binding = ItemExerciseLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ExerciseAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final float[] resistance = {mExercises.get(position).resistance};
        final float[] repOrDuration = {mExercises.get(position).repOrDuration};

        holder.tvExerciseTitle.setText(mExercises.get(position).name);
        holder.tvExerciseSubtitle.setText("Last time ...");
        holder.tvExerciseRepOrDurationTitle.setText("Repetition:");
        holder.tvExerciseResistanceTitle.setText("Resistance (lbs):");
        holder.svExerciseResistanceSlider.setValue(mExercises.get(position).resistance);
        holder.svRepOrDurationSlider.setValue(mExercises.get(position).repOrDuration);
        holder.tvExerciseResistance.setText(String.valueOf(mExercises.get(position).resistance));
        holder.tvExerciseRepOrDuration.setText(String.valueOf(mExercises.get(position).repOrDuration));
        holder.svExerciseResistanceSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                holder.tvExerciseResistance.setText(String.valueOf(value));
                resistance[0] = value;
            }
        });
        holder.svExerciseResistanceSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                mExercises.get(position).resistance = resistance[0];
                fragment.updateExercise(mExercises.get(position));
            }
        });

        holder.svRepOrDurationSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                holder.tvExerciseRepOrDuration.setText(String.valueOf((int) value));
                repOrDuration[0] = value;
            }
        });

        holder.svRepOrDurationSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                mExercises.get(position).repOrDuration = (int) repOrDuration[0];
                fragment.updateExercise(mExercises.get(position));
            }
        });

        holder.btnCompleteExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExercises.get(position).setNumber++;
                holder.tvCompletedSet.setText("Completed "+mExercises.get(position).setNumber+" Set");
                fragment.updateExercise(mExercises.get(position));
            }
        });
        holder.ibMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, holder.ibMore);
                popup.getMenuInflater().inflate(R.menu.routine_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_delete_routine){
                            fragment.deleteExercise(mExercises.get(position));
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }




    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    public void setExercises(RoutineWithExercise routineWithExercise) {
        Log.i("Exercise Adapter", "set Exercise");
        Log.i("Exercise Adapter", routineWithExercise.exercises.toString());

        this.mExercises = routineWithExercise.exercises;
        this.mRoutine = routineWithExercise.routine;
        notifyDataSetChanged();
    }

    public void completeWorkout(){
        mRoutine.lastTrained = (new Date()).getTime();
        fragment.updateRoutine(mRoutine);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvExerciseTitle;
        private final TextView tvExerciseSubtitle;
        private final TextView tvExerciseResistanceTitle;
        private final TextView tvExerciseResistance;
        private final TextView tvExerciseRepOrDurationTitle;
        private final TextView tvExerciseRepOrDuration;
        private final Slider svExerciseResistanceSlider;
        private final Slider svRepOrDurationSlider;
        private final Button btnCompleteExercise;
        private final TextView tvCompletedSet;
        private final ImageButton ibMore;
        public ViewHolder(@NonNull ItemExerciseLayoutBinding itemView ) {
            super(itemView.getRoot());
            this.tvExerciseTitle = itemView.tvExerciseTitle;
            this.tvExerciseSubtitle = itemView.tvExerciseSubtitle;
            this.tvExerciseResistanceTitle = itemView.tvExerciseResistanceTitle;
            this.tvExerciseResistance = itemView.tvExerciseResistance;
            this.svExerciseResistanceSlider = itemView.svExerciseResistanceSlider;
            this.svRepOrDurationSlider = itemView.svRepOrDurationSlider;
            this.tvExerciseRepOrDurationTitle = itemView.tvExerciseRepOrDurationTitle;
            this.tvExerciseRepOrDuration = itemView.tvExerciseRepOrDuration;
            this.btnCompleteExercise = itemView.btnCompleteExercise;
            this.tvCompletedSet = itemView.tvCompletedSet;
            this.ibMore = itemView.ibMore;

        }
    }
}


