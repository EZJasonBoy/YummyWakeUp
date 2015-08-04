package com.capricorn.yummy.yummywakeup;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvCurrentTime = (TextView) findViewById(R.id.tv_curentTime);
        TextView tvWeekMonthDay = (TextView) findViewById(R.id.tv_week_month_day);
    }
}
