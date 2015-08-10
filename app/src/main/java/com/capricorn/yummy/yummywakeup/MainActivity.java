package com.capricorn.yummy.yummywakeup;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.capricorn.yummy.yummywakeup.alarm.Alarm;
import com.capricorn.yummy.yummywakeup.alarm.Alarms;
import com.capricorn.yummy.yummywakeup.config.PreferenceKeys;
import com.capricorn.yummy.yummywakeup.model.Time;

import java.util.Calendar;

public class MainActivity extends Activity {

    private TextView tvCurrentTime;
    private TextView tvWeekMonthDay;
    private TextView tvAlarmTime;

    private int alarmId;

    private final static int ALARM_NOT_SET = -1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCurrentTime = (TextView) findViewById(R.id.tv_curentTime);
        tvWeekMonthDay = (TextView) findViewById(R.id.tv_week_month_day);
        // Start to show current time
        timeHandler.sendEmptyMessage(0);

        tvAlarmTime = (TextView) findViewById(R.id.tv_alarmTime);

        initAlarm();
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

                Alarm alarm = new Alarm();
                alarm.id = alarmId;
                alarm.enabled = true;
                alarm.hour = hourOfDay;
                alarm.minutes = minute;
                alarm.label = "闹钟 巴拉拉";

                long time;
                if (alarm.id == ALARM_NOT_SET) {
                    // ToDo Not possible to go into this bloc
                    // Is time here the Id of alarm?
                    // If alarm not set yet, set a new alarm
                    time = Alarms.addAlarm(MainActivity.this, alarm);
                    // addAlarm populates the alarm with the new id. Update mId so that
                    // changes to other preferences update the new alarm.
                    alarmId = alarm.id;
                } else {
                    time = Alarms.setAlarm(MainActivity.this, alarm);
                }

                Toast.makeText(MainActivity.this,
                        Alarms.formatToast(MainActivity.this, time),
                        Toast.LENGTH_SHORT).show();
                // Set alarm time on TextView
                setAlarmTimeOnTextView(alarm);
            }
        },c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        // ToDo why not save here?
    }

    private void initAlarm(){
        // Read saved alarm time from sharedPreference
        alarmId = readSavedAlarm();
        
        if (alarmId == ALARM_NOT_SET){
            // If no alarm available, set a default alarm with current time
            Alarm alarm = new Alarm();
            // Set alarm time on TextView
            setAlarmTimeOnTextView(alarm);
            alarmId = Alarms.addAlarm(this, alarm);
            saveAlarm();
        }else {
            Alarm alarm = Alarms.getAlarm(getContentResolver(), alarmId);
            tvAlarmTime.setText("" + alarm.hour + ":" + alarm.minutes);
        }
    }

    /**
     * Save alarm time in sharedPreference
     */
    private void saveAlarm() {
        SharedPreferences.Editor editor = this.getSharedPreferences(PreferenceKeys.SHARE_PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(PreferenceKeys.ID_CURREN_ALARM, alarmId)
                .commit();
    }

    /**
     * Read saved alarm time from sharedPreference
     * @return Id of alarm time
     */
    private int readSavedAlarm(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(PreferenceKeys.SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PreferenceKeys.ID_CURREN_ALARM, ALARM_NOT_SET);
    }

    /**
     * Set current alarm time on TextView
     * @param alarm
     */
    private void setAlarmTimeOnTextView(Alarm alarm) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, alarm.hour);
        cal.set(Calendar.MINUTE, alarm.minutes);
        tvAlarmTime.setText(Alarms.formatTime(this, cal));
    }
}
