package com.capricorn.yummy.yummywakeup.module.RingtoneModule;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;

import com.capricorn.yummy.yummywakeup.R;
import com.capricorn.yummy.yummywakeup.module.RingtoneModule.adapter.RingtoneCursorAdapter;
import com.capricorn.yummy.yummywakeup.infrastructure.activity.BaseActivity;

import java.util.List;


public class RingtoneSetting extends BaseActivity {

    private SeekBar mSeekBar;
    private ListView lvRingtone;
    private AudioManager mAudioManager;
    private Button btnAccept;
    private Button btnCancel;

    private Cursor cursor;
    private List<String> listUri;

    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void initView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        lvRingtone = (ListView) findViewById(R.id.lv_ringtone);
        btnAccept = (Button) findViewById(R.id.btn_ringtone_accept);
        btnCancel = (Button) findViewById(R.id.btn__ringtone_cancel);
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
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                cursor.moveToPosition(RingtoneCursorAdapter.selectPostion);
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                intent.putExtra("uri", MediaStore.Audio.Media.getContentUri(title).toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        /**
         * Get all the system alarm
         */
        ContentResolver cr = this.getContentResolver();
        cursor = cr.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.TITLE }, "is_alarm != ?",
                new String[] { "0" }, "_id asc");
        if (cursor == null) {
            return;
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
