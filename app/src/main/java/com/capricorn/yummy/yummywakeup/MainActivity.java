package com.capricorn.yummy.yummywakeup;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.capricorn.yummy.yummywakeup.model.Alarm;
import com.capricorn.yummy.yummywakeup.alarm.Alarms;
import com.capricorn.yummy.yummywakeup.config.PreferenceKeys;
import com.capricorn.yummy.yummywakeup.model.CurrentTime;
import com.capricorn.yummy.yummywakeup.model.DaysOfWeek;

import java.util.Calendar;

public class MainActivity extends Activity {

    private TextView tvCurrentTime;
    private TextView tvWeekMonthDay;
    private TextView tvAlarmTime;
    private TextView tvRingTone;

    private Button btnMonday;
    private Button btnTuesday;
    private Button btnWednesday;
    private Button btnThursday;
    private Button btnFriday;
    private Button btnSaturday;
    private Button btnSunday;

    private Switch swAlarm;
    private Switch swVibrate;

    private Alarm alarm;
    private int alarmId;

    private final static int ALARM_NOT_SET = -1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCurrentTime  = (TextView) findViewById(R.id.tv_curentTime);
        tvWeekMonthDay = (TextView) findViewById(R.id.tv_week_month_day);
        tvAlarmTime    = (TextView) findViewById(R.id.tv_alarmTime);
        tvRingTone     = (TextView) findViewById(R.id.tv_ringtone);

        btnMonday    = (Button) findViewById(R.id.btn_monday);
        btnTuesday   = (Button) findViewById(R.id.btn_tuesday);
        btnWednesday = (Button) findViewById(R.id.btn_wednesday);
        btnThursday  = (Button) findViewById(R.id.btn_thursday);
        btnFriday    = (Button) findViewById(R.id.btn_friday);
        btnSaturday  = (Button) findViewById(R.id.btn_saturday);
        btnSunday    = (Button) findViewById(R.id.btn_sunday);

        swAlarm = (Switch) findViewById(R.id.sw_alarm);
        swVibrate = (Switch) findViewById(R.id.sw_vibrate);

        initAlarm();
        initRepeat();
        initSwitch();
        initListener();
    }

    /**
     * Init alarm
     */
    private void initAlarm() {
        // Start to show current time
        timeHandler.sendEmptyMessage(0);
        // Read saved alarm time from sharedPreference
        alarmId = readSavedAlarm();

        if (alarmId == ALARM_NOT_SET){
            // If no alarm available, set a default alarm with current time
            alarm = new Alarm();
            // Set alarm time on TextView
            setAlarmTimeOnTextView(alarm);
            alarmId = Alarms.addAlarm(this, alarm);
            saveAlarm();
        }else {
            alarm = Alarms.getAlarm(getContentResolver(), alarmId);
            setAlarmTimeOnTextView(alarm);
        }
    }

    /**
     * Init repeat buttons' status
     */
    private void initRepeat() {
        if(alarm.daysOfWeek.isRepeatSet()){
            btnMonday.setActivated(alarm.daysOfWeek.isSet(DaysOfWeek.MONDAY));
            btnTuesday.setActivated(alarm.daysOfWeek.isSet(DaysOfWeek.TUESDAY));
            btnWednesday.setActivated(alarm.daysOfWeek.isSet(DaysOfWeek.WEDNESDAY));
            btnThursday.setActivated(alarm.daysOfWeek.isSet(DaysOfWeek.THURSDAY));
            btnFriday.setActivated(alarm.daysOfWeek.isSet(DaysOfWeek.FRIDAY));
            btnSaturday.setActivated(alarm.daysOfWeek.isSet(DaysOfWeek.SATURDAY));
            btnSunday.setActivated(alarm.daysOfWeek.isSet(DaysOfWeek.SUNDAY));
        }
    }

    /**
     * Init switch status when start app
     */
    private void initSwitch() {
        if(alarm.enabled) swAlarm.setChecked(true);
        if(alarm.vibrate) swVibrate.setChecked(true);
    }

    /**
     * Set listeners for switch alarm and switch vibrate
     */
    private void initListener() {
        // Set alarm switch
        swAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Alarms.setAlarm(MainActivity.this, alarm);
                } else {
                    // ToDo bug here, not work
                    Alarms.deleteAlarm(MainActivity.this, alarm);
                }
            }
        });
        // Set vibrate switch
        swVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // ToDo Set vibrate
                } else {
                    // ToDo Unset vibrate
                }
            }
        });
        // Set RingtoneSetting
        tvRingTone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RingtoneSetting.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private Handler timeHandler = new Handler(){
        public void handleMessage(Message msg) {
            RefreshCurrentTime();
            timeHandler.sendEmptyMessageDelayed(0, 1000); // Update time Every one second
        }
    };

    /**
     * Refresh time shown on TextView
     */
    private void RefreshCurrentTime() {
        Calendar c = Calendar.getInstance();
        CurrentTime currentTime = new CurrentTime(c.getTimeInMillis());
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

                alarm = new Alarm();
                alarm.id = alarmId;
                alarm.enabled = true;
                alarm.hour = hourOfDay;
                alarm.minutes = minute;
                alarm.label = "闹钟 巴拉拉";
                alarm.unlockType = Alarm.AlarmUnlockType.Normal.value();

                long time = Alarms.setAlarm(MainActivity.this, alarm);

                Toast.makeText(MainActivity.this,
                        Alarms.formatToast(MainActivity.this, time),
                        Toast.LENGTH_SHORT).show();
                // Set alarm time on TextView
                setAlarmTimeOnTextView(alarm);
            }
        },c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }

    /**
     * Save alarm time in sharedPreference
     */
    private void saveAlarm() {
        SharedPreferences.Editor editor = this.getSharedPreferences(PreferenceKeys.SHARE_PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(PreferenceKeys.KEY_ALARM_ID, alarmId)
                .commit();
    }

    /**
     * Read saved alarm time from sharedPreference
     * @return Id of alarm time
     */
    private int readSavedAlarm(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(PreferenceKeys.SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PreferenceKeys.KEY_ALARM_ID, ALARM_NOT_SET);
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

    /**
     * Listenr for the repeat buttons (Monday, Tuesday and so on)
     * @param view
     */
    public void setRepeat(View view) {
        if(view.isActivated()) {
            view.setActivated(false);
        } else {
            view.setActivated(true);
        }
        switch (view.getId()) {
            case R.id.btn_monday:
                alarm.daysOfWeek.set(DaysOfWeek.MONDAY, view.isActivated());
                break;
            case R.id.btn_tuesday:
                alarm.daysOfWeek.set(DaysOfWeek.TUESDAY, view.isActivated());
                break;
            case R.id.btn_wednesday:
                alarm.daysOfWeek.set(DaysOfWeek.WEDNESDAY, view.isActivated());
                break;
            case R.id.btn_thursday:
                alarm.daysOfWeek.set(DaysOfWeek.THURSDAY, view.isActivated());
                break;
            case R.id.btn_friday:
                alarm.daysOfWeek.set(DaysOfWeek.FRIDAY, view.isActivated());
                break;
            case R.id.btn_saturday:
                alarm.daysOfWeek.set(DaysOfWeek.SATURDAY, view.isActivated());
                break;
            case R.id.btn_sunday:
                alarm.daysOfWeek.set(DaysOfWeek.SUNDAY, view.isActivated());
                break;
            default:
                break;
        }
        Alarms.setAlarm(MainActivity.this, alarm);
    }

    private void setColor() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if(resultCode == RESULT_OK) {
                    alarm.alert = Uri.parse(data.getStringExtra("uri"));
                    Alarms.setAlarm(MainActivity.this, alarm);
                }
                break;
            default:
                break;
        }
    }
}
