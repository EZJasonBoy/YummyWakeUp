package com.capricorn.yummy.yummywakeup.alarmType;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.capricorn.yummy.yummywakeup.R;
import com.capricorn.yummy.yummywakeup.alarm.AlarmAlertFullScreen;

/**
 * Created by Chuan on 8/4/2015.
 */
public class NormalAlarm extends UnlockFragment {

    private Button btnCloseAlarm;
    private OnAlarmAction mListener;

    @Override
    public void initView(View container) {
        btnCloseAlarm = (Button) container.findViewById(R.id.btn_close_alarm);
    }

    @Override
    public void initConfig(Bundle bundle) {}

    @Override
    public int getLayoutId() {
        return R.layout.fragment_normal_alarm;
    }

    @Override
    public void initListener() {
        btnCloseAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeAlarm();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (AlarmAlertFullScreen) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void refresh() {}

    @Override
    public boolean checkUnloclAlarm() {
        // Nothing to do for normal alarm
        return true;
    }
}
