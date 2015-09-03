package com.capricorn.yummy.yummywakeup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.R;

/**
 * Created by chuandong on 15/8/31.
 */
public class UnlockTypeAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] mTypeNames;
    private final int[] mTypeImages;

    public UnlockTypeAdapter(Context c,String[] typeNames,int[] typeImages) {
        mContext = c;
        this.mTypeNames = typeNames;
        this.mTypeImages = typeImages;
    }

    @Override
    public int getCount() {
        return mTypeNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_unlock_type, null);
            TextView textView = (TextView) grid.findViewById(R.id.tv_unlock_type_item);
            ImageButton imageView = (ImageButton) grid.findViewById(R.id.ib_unlock_type_item);
            textView.setText(mTypeNames[position]);
            imageView.setImageResource(mTypeImages[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
