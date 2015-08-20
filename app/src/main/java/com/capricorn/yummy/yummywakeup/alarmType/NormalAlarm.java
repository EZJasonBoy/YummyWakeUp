package com.capricorn.yummy.yummywakeup.alarmType;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capricorn.yummy.yummywakeup.R;
import com.capricorn.yummy.yummywakeup.alarm.AlarmAlertFullScreen;

/**
 * Created by Chuan on 8/4/2015.
 */
public class NormalAlarm extends Fragment implements View.OnClickListener {

//    private MediaPlayer mMediaPlayer;

    private View closeAlarm;
    private OnAlarmAction mListener;
    
/*    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        mMediaPlayer = MediaPlayer.create(activity, R.raw.music_1);
//        mMediaPlayer.start();
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.alarm_player, container, false);
        closeAlarm = rootView.findViewById(R.id.closeAlarm);
        closeAlarm.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
//        mMediaPlayer.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mMediaPlayer.stop();
//        mMediaPlayer.release();
    }

    @Override
    public void onClick(View v) {

        mListener.closeAlarm();
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
    public interface OnAlarmAction{
        void closeAlarm();
    }

    
}
