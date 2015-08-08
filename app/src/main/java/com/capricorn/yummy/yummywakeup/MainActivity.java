package com.capricorn.yummy.yummywakeup;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.capricorn.yummy.yummywakeup.model.Time;
import com.capricorn.yummy.yummywakeup.service.AlarmReceiver;

import java.util.Calendar;

public class MainActivity extends Activity {

    private TextView tvCurrentTime;
    private TextView tvWeekMonthDay;
    private TextView tvAlarmTime;

    private AlarmManager alarmManager;

    private static final int LOCK_TYPE_NORMAL = 1;
    private static final String SP_ALARM_TIME = "YummyAlarmTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCurrentTime = (TextView) findViewById(R.id.tv_curentTime);
        tvWeekMonthDay = (TextView) findViewById(R.id.tv_week_month_day);
        timeHandler.sendEmptyMessage(0); // Start to show current time

        tvAlarmTime = (TextView) findViewById(R.id.tv_alarmTime);
        readSavedAlarmList();


    }

    private Handler timeHandler = new Handler(){
        public void handleMessage(Message msg) {
            refreshTime();
            timeHandler.sendEmptyMessageDelayed(0, 1000); // Update time Every one second
        }
    };

    /**
     * Refresh time shown on TextView
     */
    private void refreshTime() {
        Calendar c = Calendar.getInstance();
        Time currentTime = new Time(c.getTimeInMillis());
        // Update time shown on TextView
        tvCurrentTime.setText(currentTime.getTimeLabel());
        tvWeekMonthDay.setText(currentTime.getWeekMonthDayLabel());
    }

    /**
     * OnClick for relativelayout to set alarm time
     * @param view
     */
    public void setAlarmTime(View view) {

        final Calendar c = Calendar.getInstance();

        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                Calendar timeSetting = Calendar.getInstance();

                timeSetting.set(Calendar.HOUR_OF_DAY, hourOfDay);
                timeSetting.set(Calendar.MINUTE, minute);
                timeSetting.set(Calendar.SECOND, 0);
                timeSetting.set(Calendar.MILLISECOND, 0);

                // If setting time is earlier than current time, day + 1
                if (timeSetting.getTimeInMillis() <= c.getTimeInMillis()) {
                    timeSetting.setTimeInMillis(timeSetting.getTimeInMillis()+24*60*60*1000);
                }

                // Setting alarm
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        timeSetting.getTimeInMillis(),
                        24*60*60*1000,
                        PendingIntent.getBroadcast(getApplicationContext(), LOCK_TYPE_NORMAL, new Intent(getApplicationContext(), AlarmReceiver.class), 0));
                // Save alarm time
                saveAlarmList();
            }
        },c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }

    /**
     * Save alarm time in sharedPreference
     */
    private void saveAlarmList() {
        SharedPreferences.Editor editor = this.getSharedPreferences(this.getLocalClassName(), Context.MODE_PRIVATE).edit();

        editor.putString(SP_ALARM_TIME, tvAlarmTime.getText().toString())
                .commit();
    }

    /**
     * Read alarm time from sharedPreference
     */
    private void readSavedAlarmList(){
        SharedPreferences sp = this.getSharedPreferences(this.getLocalClassName(), Context.MODE_PRIVATE);
        tvAlarmTime.setText(sp.getString(SP_ALARM_TIME, null));
    }

}
