package com.capricorn.yummy.yummywakeup;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.model.Time;

import java.util.Calendar;

public class MainActivity extends Activity {

    private TextView tvCurrentTime;
    private TextView tvWeekMonthDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCurrentTime = (TextView) findViewById(R.id.tv_curentTime);
        tvWeekMonthDay = (TextView) findViewById(R.id.tv_week_month_day);
        timerHandler.sendEmptyMessage(0); // Start to show current time

    }

    private Handler timerHandler = new Handler(){
        public void handleMessage(Message msg) {
            refreshTime();
            timerHandler.sendEmptyMessageDelayed(0, 1000); // Update time Every one second
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
}
