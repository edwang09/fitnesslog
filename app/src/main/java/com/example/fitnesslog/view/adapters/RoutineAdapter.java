package com.example.fitnesslog.view.adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.ItemRoutineLayoutBinding;
import com.example.fitnesslog.model.entities.Exercise;
import com.example.fitnesslog.model.entities.Routine;
import com.example.fitnesslog.view.fragments.AllWorkoutFragment;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> {
    private List<Routine> mRoutines;
    private final Context context;
    private final AllWorkoutFragment fragment;

    public RoutineAdapter(AllWorkoutFragment fragment){
        this.fragment = fragment;
        this.context = fragment.getContext();
        mRoutines = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRoutineLayoutBinding binding = ItemRoutineLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Routine r = mRoutines.get(position);
        holder.flBackground.setBackgroundColor(r.color);
        holder.tvRoutineTitle.setText(r.name);
        if (r.lastTrained != 0){
            holder.tvRoutineSubtitle.setText("Last time Trained " + ((new Date()).getTime()- (new Date(r.lastTrained)).getTime())/(1000*60*60 * 24) + " days ago");
        }else{
            holder.tvRoutineSubtitle.setText("You have never trained this routine yet.");
        }
        holder.tvType.setText(r.type);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.workoutFragment(r.routineId);
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
                            fragment.deleteRoutine(r);
                        } else if (item.getItemId() == R.id.action_edit_routine){
                            fragment.editRoutine(r);
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
        return mRoutines.size();
    }

    public void setRoutines(List<Routine> mRoutines) {
        this.mRoutines = mRoutines;

        Log.i("Routine Adapter", "set Routine");
        for (Routine r : mRoutines){
            Log.i("Routine ", String.valueOf(r.routineId));
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout flBackground;
        private final TextView tvType;
        private final TextView tvRoutineTitle;
        private final TextView tvRoutineSubtitle;
        private final ImageButton ibMore;
        public ViewHolder(@NonNull ItemRoutineLayoutBinding itemView ) {
            super(itemView.getRoot());
            this.flBackground = itemView.flBackground;
            this.tvType = itemView.tvType;
            this.tvRoutineTitle = itemView.tvRoutineTitle;
            this.tvRoutineSubtitle = itemView.tvRoutineSubtitle;
            this.ibMore = itemView.ibMore;
        }
    }
}
