package com.capricorn.yummy.yummywakeup;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SeekBar;

import com.capricorn.yummy.yummywakeup.infrastructure.activity.BaseActivity;


public class RingtoneSetting extends BaseActivity {

    private SeekBar mSeekBar;
    private ListView lvRingtone;
    private AudioManager mAudioManager;

    @Override
    public void initToolbar() {}

    @Override
    public void initView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        lvRingtone = (ListView) findViewById(R.id.lv_ringtone);
        // Init SeekBar
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        mSeekBar.setMax(maxVolume);
        mSeekBar.setProgress(currentVolume);
    }

    @Override
    public void initListener() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM,
                        progress,
                        AudioManager.FLAG_PLAY_SOUND);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ringtone_setting;
    }
}
