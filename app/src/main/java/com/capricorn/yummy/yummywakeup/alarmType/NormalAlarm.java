package com.capricorn.yummy.yummywakeup.alarmType;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.capricorn.yummy.yummywakeup.R;

/**
 * Created by Chuan on 8/4/2015.
 */
public class NormalAlarm extends Activity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_player);

        mMediaPlayer = MediaPlayer.create(this, R.raw.music_1);
        mMediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }
}
