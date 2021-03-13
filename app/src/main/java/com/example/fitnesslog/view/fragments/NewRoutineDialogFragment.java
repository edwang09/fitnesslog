package com.example.fitnesslog.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.fitnesslog.R;
import com.example.fitnesslog.databinding.DialogNewRoutineLayoutBinding;
import com.google.android.material.textfield.TextInputLayout;

public class NewRoutineDialogFragment extends DialogFragment {


    public DialogNewRoutineLayoutBinding mBinding;
    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;


    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding =  DialogNewRoutineLayoutBinding.inflate(inflater, container,false);
        return mBinding.getRoot();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();



        builder.setView(inflater.inflate(R.layout.dialog_new_routine_layout,null));
//                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        String str = mBinding.evRoutineName.getEditText().getText().toString();
//                        String str2 = ((TextInputLayout) getLayoutInflater().inflate(R.layout.dialog_new_routine_layout,null ).getRootView().findViewById(R.id.ev_routine_name)).getEditText().getText().toString();
//                        Toast.makeText(getContext() ,str,Toast.LENGTH_LONG).show();
////                        listener.onDialogPositiveClick(NewRoutineDialogFragment.this);
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        listener.onDialogPositiveClick(NewRoutineDialogFragment.this);
//                    }
//                });
        return builder.create();
    }
}