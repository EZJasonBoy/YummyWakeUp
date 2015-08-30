package com.capricorn.yummy.yummywakeup.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.R;

import java.util.HashMap;

/**
 * Created by chuandong on 15/8/29.
 */
public class RingtoneCursorAdapter extends CursorAdapter {

    public RingtoneCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    HashMap<String,Boolean> radioButtonStates=new HashMap<String,Boolean>();
    ViewHolder holder;

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_ringtone, parent, false);

        holder = new ViewHolder();
        holder.tvRingtone = (TextView) view.findViewById(R.id.tv_ringtone);
        holder.rbRingtone = (RadioButton) view.findViewById(R.id.rb_ringtone);
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        holder = (ViewHolder) view.getTag();
        // Set ringtone name
        String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
        holder.tvRingtone.setText(title);
        // Get position here instead of in onClick. We cant ensure that position is the same
        // in onClick
        final int position = cursor.getPosition();

        holder.rbRingtone.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Set radio button checked
                if (holder.rbRingtone.isChecked()) {
                    holder.rbRingtone.setChecked(false);
                } else {
                    holder.rbRingtone.setChecked(true);
                }
                // Reset all flags to make sure only one radio button is selected
                for (String key : radioButtonStates.keySet()) {
                    radioButtonStates.put(key, false);
                }

                radioButtonStates.put(String.valueOf(position), holder.rbRingtone.isChecked());
                RingtoneCursorAdapter.this.notifyDataSetChanged();
            }
        });

        boolean res = false;
        if(radioButtonStates.get(String.valueOf(position)) == null
                || radioButtonStates.get(String.valueOf(position)) == false){
            res = false;
            radioButtonStates.put(String.valueOf(position), false);
        } else {
            res = true;
        }

        holder.rbRingtone.setChecked(res);
    }

    static class ViewHolder {
        TextView tvRingtone;
        RadioButton rbRingtone;
    }
}
