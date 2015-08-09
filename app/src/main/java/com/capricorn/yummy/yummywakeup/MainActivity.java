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

    private AlarmManager alarmManager;

    private static final int LOCK_TYPE_NORMAL = 1;
    private static final String SP_ALARM_TIME = "YummyAlarmTime";

    private int alarmId;
    
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
                alarm.daysOfWeek = new Alarm.DaysOfWeek(0);
                alarm.vibrate = true;
                alarm.label = "闹钟 巴拉拉";
                alarm.alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

                long time;
                if (alarm.id == -1) {
                    time = Alarms.addAlarm(MainActivity.this, alarm);
                    // addAlarm populates the alarm with the new id. Update mId so that
                    // changes to other preferences update the new alarm.
                    alarmId = alarm.id;
                } else {
                    time = Alarms.setAlarm(MainActivity.this, alarm);
                }

                Toast.makeText(MainActivity.this,Alarms.formatToast(MainActivity.this,time),Toast.LENGTH_SHORT).show();

                final Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, alarm.hour);
                cal.set(Calendar.MINUTE, alarm.minutes);
                tvAlarmTime.setText(Alarms.formatTime(MainActivity.this, cal));
                
                
        /*        Calendar timeSetting = Calendar.getInstance();

                timeSetting.set(Calendar.HOUR_OF_DAY, hourOfDay);
                timeSetting.set(Calendar.MINUTE, minute);
                timeSetting.set(Calendar.SECOND, 0);
                timeSetting.set(Calendar.MILLISECOND, 0);

                // If setting time is earlier than current time, day + 1
                if (timeSetting.getTimeInMillis() <= c.getTimeInMillis()) {
                    timeSetting.setTimeInMillis(timeSetting.getTimeInMillis()+24*60*60*1000);
                }
*/
                // Setting alarm
               /* alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        timeSetting.getTimeInMillis(),
                        24*60*60*1000,
                        PendingIntent.getBroadcast(getApplicationContext(), LOCK_TYPE_NORMAL, new Intent(getApplicationContext(), AlarmReceiver.class), 0));*/
                // Save alarm time
                
            //    saveAlarmList();
                
            }
        },c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }

    private void initAlarm(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(PreferenceKeys.SHARE_NAME, Context.MODE_PRIVATE);
        alarmId = sharedPreferences.getInt(PreferenceKeys.PREF_CURREN_ALARM_ID, -1);
        
        if (alarmId <=0){
            //当前没闹钟,设置一个默认闹钟
            Alarm alarm = new Alarm();

            final Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, alarm.hour);
            cal.set(Calendar.MINUTE, alarm.minutes);
            final String time = Alarms.formatTime(this, cal);
           
            tvAlarmTime.setText(time);
            alarmId = Alarms.addAlarm(this,alarm);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(PreferenceKeys.PREF_CURREN_ALARM_ID, alarmId);
            editor.commit();
            
        }else {
            Alarm alarm = Alarms.getAlarm(getContentResolver(),alarmId);
            tvAlarmTime.setText(""+alarm.hour+":"+alarm.minutes);
        }
        
    }
    
    
    /**
     * Save alarm time in sharedPreference
     */
  /*  private void saveAlarmList() {
       SharedPreferences.Editor editor = this.getSharedPreferences(this.getLocalClassName(), Context.MODE_PRIVATE).edit();

        editor.putString(SP_ALARM_TIME, tvAlarmTime.getText().toString())
                .commit();
    
        
    }*/

    /**
     * Read alarm time from sharedPreference
     */
 /*   private void readSavedAlarmList(){
        SharedPreferences sp = this.getSharedPreferences(this.getLocalClassName(), Context.MODE_PRIVATE);
        tvAlarmTime.setText(sp.getString(SP_ALARM_TIME, null));
    }*/

}
