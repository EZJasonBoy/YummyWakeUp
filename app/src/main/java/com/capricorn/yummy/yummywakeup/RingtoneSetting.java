package com.capricorn.yummy.yummywakeup;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.adapter.RingtoneCursorAdapter;
import com.capricorn.yummy.yummywakeup.infrastructure.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class RingtoneSetting extends BaseActivity {

    private SeekBar mSeekBar;
    private ListView lvRingtone;
    private AudioManager mAudioManager;
    private TextView tvItemRingtone;

    List<String> list_alarm = new ArrayList<String>();

    @Override
    public void initToolbar() {}

    @Override
    public void initView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        lvRingtone = (ListView) findViewById(R.id.lv_ringtone);
        tvItemRingtone = (TextView) findViewById(R.id.tv_ringtone);
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
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void initData() {

        ContentResolver cr = this.getContentResolver();
        Cursor cursor = cr.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.TITLE }, "is_alarm != ?",
                new String[] { "0" }, "_id asc");
        if (cursor == null) {
            return;
        }
        while (cursor.moveToNext()) {
            list_alarm.add(cursor.getString(1));
        }

        lvRingtone.setAdapter(new RingtoneCursorAdapter(this, cursor, 0));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ringtone_setting;
    }

        //ringtone
//        cursor = cr.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
//                new String[] { MediaStore.Audio.Media._ID,
//                        MediaStore.Audio.Media.DATA,
//                        MediaStore.Audio.Media.TITLE }, "is_ringtone != ?",
//                new String[] { "0" }, "_id asc");
//        if (cursor == null) {
//            return;
//        }
//        while (cursor.moveToNext()) {
//            list_ringtone.add(cursor.getString(1));
//        }

}
