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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        holder.tvExerciseTitle.setText(mExercises.get(position).name);
        holder.ivExerciseImage.setImageResource(mExercises.get(position).image);
        holder.tvExerciseSubtitle.setText("Complete " + mExercises.get(position).lastTime + " Sets (or more) of this exercise.");
        if(mExercises.get(position).hasResistance) {
            final float[] resistance = {mExercises.get(position).resistance};
            holder.tvExerciseResistanceTitle.setText("Resistance (lbs):");
            holder.svExerciseResistanceSlider.setValue(resistance[0]);
            holder.tvExerciseResistance.setText(String.valueOf(resistance[0]));
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
            holder.lvExerciseResistance.setVisibility(View.VISIBLE);
        }
        if(mExercises.get(position).hasDuration) {
            final int[] duration = {mExercises.get(position).duration};
            holder.tvExerciseDurationTitle.setText("Duration (min):");
            holder.svDurationSlider.setValue(duration[0]);
            holder.tvExerciseDuration.setText(String.valueOf(duration[0]));
            holder.svDurationSlider.addOnChangeListener(new Slider.OnChangeListener() {
                @Override
                public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                    holder.tvExerciseDuration.setText(String.valueOf(value));
                    duration[0] = (int) value;
                }
            });
            holder.svDurationSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(@NonNull Slider slider) {

                }

                @Override
                public void onStopTrackingTouch(@NonNull Slider slider) {
                    mExercises.get(position).duration = duration[0];
                    fragment.updateExercise(mExercises.get(position));
                }
            });
            holder.lvExerciseDuration.setVisibility(View.VISIBLE);
        }
        if(mExercises.get(position).hasRep) {
            final int[] rep = {mExercises.get(position).rep};
            holder.tvExerciseRepTitle.setText("Repetition : ");
            holder.svRepSlider.setValue(rep[0]);
            holder.tvExerciseRep.setText(String.valueOf(rep[0]));
            holder.svRepSlider.addOnChangeListener(new Slider.OnChangeListener() {
                @Override
                public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                    holder.tvExerciseRep.setText(String.valueOf(value));
                    rep[0] = (int) value;
                }
            });
            holder.svRepSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(@NonNull Slider slider) {

                }

                @Override
                public void onStopTrackingTouch(@NonNull Slider slider) {
                    mExercises.get(position).rep = rep[0];
                    fragment.updateExercise(mExercises.get(position));
                }
            });
            holder.lvExerciseRep.setVisibility(View.VISIBLE);
        }
        if(mExercises.get(position).hasSpeed) {
            final float[] speed = {mExercises.get(position).speed};
            holder.tvExerciseSpeedTitle.setText("Speed (mph):");
            holder.svSpeedSlider.setValue(speed[0]);
            holder.tvExerciseSpeed.setText(String.valueOf(speed[0]));
            holder.svSpeedSlider.addOnChangeListener(new Slider.OnChangeListener() {
                @Override
                public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                    holder.tvExerciseSpeed.setText(String.valueOf(value));
                    speed[0] = value;
                }
            });
            holder.svSpeedSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(@NonNull Slider slider) {

                }

                @Override
                public void onStopTrackingTouch(@NonNull Slider slider) {
                    mExercises.get(position).speed = speed[0];
                    fragment.updateExercise(mExercises.get(position));
                }
            });
            holder.lvExerciseSpeed.setVisibility(View.VISIBLE);
        }

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
                popup.getMenuInflater().inflate(R.menu.exercise_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_delete_exercise){
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
        if (mExercises.size()==0){
            fragment.switchNoExerciseHolder(true);
        }else{
            fragment.switchNoExerciseHolder(false);
        }
        notifyDataSetChanged();
    }

    public void completeWorkout(){
        mRoutine.lastTrained = (new Date()).getTime();
        for (Exercise exercise : mExercises){
            exercise.lastTime = exercise.setNumber;
            exercise.setNumber = 0;
            fragment.updateExercise(exercise);
        }
        fragment.updateRoutine(mRoutine);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvExerciseTitle;
        private final TextView tvExerciseSubtitle;
        private final TextView tvExerciseResistanceTitle;
        private final TextView tvExerciseResistance;
        private final TextView tvExerciseRepTitle;
        private final TextView tvExerciseRep;
        private final TextView tvExerciseDurationTitle;
        private final TextView tvExerciseDuration;
        private final TextView tvExerciseSpeedTitle;
        private final TextView tvExerciseSpeed;
        private final Slider svExerciseResistanceSlider;
        private final Slider svRepSlider;
        private final Slider svDurationSlider;
        private final Slider svSpeedSlider;
        private final Button btnCompleteExercise;
        private final TextView tvCompletedSet;
        private final ImageButton ibMore;
        private final LinearLayout lvExerciseDuration;
        private final LinearLayout lvExerciseRep;
        private final LinearLayout lvExerciseResistance;
        private final LinearLayout lvExerciseSpeed;
        private final ImageView ivExerciseImage;
        public ViewHolder(@NonNull ItemExerciseLayoutBinding itemView ) {
            super(itemView.getRoot());
            this.tvExerciseTitle = itemView.tvExerciseTitle;
            this.tvExerciseSubtitle = itemView.tvExerciseSubtitle;
            this.tvExerciseResistanceTitle = itemView.tvExerciseResistanceTitle;
            this.tvExerciseResistance = itemView.tvExerciseResistance;
            this.svExerciseResistanceSlider = itemView.svExerciseResistanceSlider;
            this.svRepSlider = itemView.svRepSlider;
            this.tvExerciseRepTitle = itemView.tvExerciseRepTitle;
            this.tvExerciseRep = itemView.tvExerciseRep;
            this.btnCompleteExercise = itemView.btnCompleteExercise;

            this.tvExerciseDurationTitle = itemView.tvExerciseDurationTitle;
            this.tvExerciseDuration = itemView.tvExerciseDuration;
            this.svDurationSlider = itemView.svDurationSlider;

            this.tvExerciseSpeedTitle = itemView.tvExerciseSpeedTitle;
            this.tvExerciseSpeed = itemView.tvExerciseSpeed;
            this.svSpeedSlider = itemView.svSpeedSlider;


            this.tvCompletedSet = itemView.tvCompletedSet;
            this.ibMore = itemView.ibMore;
            this.lvExerciseDuration = itemView.lvExerciseDuration;
            this.lvExerciseRep = itemView.lvExerciseRep;
            this.lvExerciseResistance = itemView.lvExerciseResistance;
            this.lvExerciseSpeed = itemView.lvExerciseSpeed;
            this.ivExerciseImage = itemView.ivExerciseImage;

        }
    }
}


