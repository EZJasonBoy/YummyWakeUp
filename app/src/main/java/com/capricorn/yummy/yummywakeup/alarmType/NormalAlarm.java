package com.capricorn.yummy.yummywakeup.alarmType;

import android.app.Activity;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capricorn.yummy.yummywakeup.R;

/**
 * Created by Chuan on 8/4/2015.
 */
public class NormalAlarm extends Fragment {

    private MediaPlayer mMediaPlayer;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMediaPlayer = MediaPlayer.create(activity, R.raw.music_1);
        mMediaPlayer.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alarm_player, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mMediaPlayer.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }
}
