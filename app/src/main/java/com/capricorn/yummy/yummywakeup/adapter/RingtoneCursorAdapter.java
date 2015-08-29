package com.capricorn.yummy.yummywakeup.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.R;

/**
 * Created by chuandong on 15/8/29.
 */
public class RingtoneCursorAdapter extends CursorAdapter {

    public RingtoneCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_ringtone, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
        ((TextView)view.findViewById(R.id.tv_ringtone)).setText(title);
    }
}
