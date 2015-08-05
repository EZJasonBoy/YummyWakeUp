package com.capricorn.yummy.yummywakeup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvCurrentTime = (TextView) findViewById(R.id.tv_curentTime);
        TextView tvWeekMonthDay = (TextView) findViewById(R.id.tv_week_month_day);
    }
}
