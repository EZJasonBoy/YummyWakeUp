package com.capricorn.yummy.yummywakeup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.R;

import java.util.List;

/**
 * Created by chuandong on 15/8/31.
 */
public class UnlockTypeAdapter extends BaseAdapter {

    private Context mContext;
   /* private final String[] mTypeNames;
    private final int[] mTypeImages;*/
    private List<UnlockType> unlockTypes;
    LayoutInflater inflater;
    public UnlockTypeAdapter(Context c,List<UnlockType> data) {
        mContext = c;
        inflater = LayoutInflater.from(mContext);
       /* this.mTypeNames = typeNames;
        this.mTypeImages = typeImages;*/
        unlockTypes = data;
    }

    @Override
    public int getCount() {
        return unlockTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return unlockTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_unlock_type, null);

            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.ib_unlock_type_item);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_unlock_type_item);
            convertView.setTag(holder);
            
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        UnlockType unlockType = unlockTypes.get(position);
        holder.textView.setText(unlockType.mTypeNames);
        holder.imageView.setImageResource(unlockType.mTypeImages);
        return convertView;
    }
    
    
    class ViewHolder {
        TextView textView ;
        ImageView imageView;
    }
    
    
    public static class UnlockType{
       public String mTypeNames;
       public int mTypeImages;
    }
    
}
