package com.capricorn.yummy.yummywakeup.module.UnlockTypeModule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.R;
import com.capricorn.yummy.yummywakeup.model.Alarm;
import com.capricorn.yummy.yummywakeup.module.UnlockTypeModule.model.UnlockDiffcultLevel;
import com.capricorn.yummy.yummywakeup.module.UnlockTypeModule.model.UnlockTypeItem;

import java.util.List;

/**
 * Created by chuandong on 15/8/31.
 */
public class UnlockTypeAdapter extends BaseAdapter {

    private Context mContext;
    private List<UnlockTypeItem> unlockTypes;
    private Alarm mAlarm;
    LayoutInflater inflater;

    public UnlockTypeAdapter(Context c, List<UnlockTypeItem> data, Alarm alarm) {
        mContext = c;
        inflater = LayoutInflater.from(mContext);
        unlockTypes = data;
        mAlarm = alarm;
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
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_unlock_type_item);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_unlock_type_item);
            holder.tvDiffLvl = (TextView) convertView.findViewById(R.id.tv_diff_lvl);
            convertView.setTag(holder);
            
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        UnlockTypeItem unlockType = unlockTypes.get(position);
        holder.textView.setText(unlockType.getTypeName());
        holder.imageView.setImageResource(unlockType.getTypeImage());
        if(position == mAlarm.unlockType && position != 0) { // If selected and not normal unlocktype
            holder.tvDiffLvl.setText(UnlockDiffcultLevel.valueString(mAlarm.unlockDiffLevel));
            holder.imageView.setBackgroundResource(R.color.alizarin);
        } else if(position == 0) { // If normal unlocktype, no need to show diff lvl
            holder.tvDiffLvl.setVisibility(View.GONE);
        } else {
            holder.tvDiffLvl.setText(unlockType.getmDiffLvl());
        }
        return convertView;
    }
    
    class ViewHolder {
        TextView textView;
        TextView tvDiffLvl;
        ImageView imageView;
    }
    
}
