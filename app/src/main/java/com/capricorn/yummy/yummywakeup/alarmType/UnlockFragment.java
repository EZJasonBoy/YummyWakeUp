package com.capricorn.yummy.yummywakeup.alarmType;

import com.capricorn.yummy.yummywakeup.infrastructure.fragment.BaseFragment;

/**
 * Created by chuandong on 15/8/23.
 */
public abstract class UnlockFragment extends BaseFragment {


    public abstract boolean checkUnloclAlarm();

    /**
     * Callback interface to unlock and close alarm
     */
    public interface OnAlarmAction{

        void closeAlarm();
    }
}
