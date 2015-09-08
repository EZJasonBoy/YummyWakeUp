package com.capricorn.yummy.yummywakeup.unlockTypeModule.dialog;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.capricorn.yummy.yummywakeup.R;

/**
 * Created by chuandong on 15/8/31.
 */
public class UnlockDialogFragment extends DialogFragment {

    private NumberPicker numberPicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_unlock_hard_lvl_picker, null);
        numberPicker = (NumberPicker) dialogView.findViewById(R.id.np_hard_lvl);
        String[] values = new String[3];
        values[0] = "Easy";
        values[1] = "Normal";
        values[2] = "Hard";
        numberPicker.setMaxValue(2);
        numberPicker.setMinValue(0);
        numberPicker.setDisplayedValues(values);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView)
                .setTitle("Select difficulty level")
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Todo
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        return builder.create();

    }
}
