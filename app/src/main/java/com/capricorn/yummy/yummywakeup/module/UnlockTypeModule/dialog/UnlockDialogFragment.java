package com.capricorn.yummy.yummywakeup.module.UnlockTypeModule.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.capricorn.yummy.yummywakeup.R;

/**
 * Created by chuandong on 15/8/31.
 */
public class UnlockDialogFragment extends DialogFragment {

    private NumberPicker numberPicker;
    private Handler mResponseHandler;
    private String[] values = {"Easy", "Normal", "Hard"};
    private Bundle savedInstanceState;
    private int mIdButton;

    public UnlockDialogFragment(Handler responsHandler, int IdButton) {
        mResponseHandler = responsHandler;
        mIdButton = IdButton;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_unlock_hard_lvl_picker, null);
        numberPicker = (NumberPicker) dialogView.findViewById(R.id.np_hard_lvl);
        numberPicker.setMaxValue(2);
        numberPicker.setMinValue(0);
        numberPicker.setValue(1);
        numberPicker.setDisplayedValues(values);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView)
                .setTitle("Select difficulty level")
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Message msg = new Message();
                        msg.what = mIdButton;
                        msg.arg1 = numberPicker.getValue();
                        mResponseHandler.sendMessage(msg);
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
