package com.capricorn.yummy.yummywakeup;

import android.app.Application;

import com.capricorn.yummy.yummywakeup.config.RunTime;

/**
 * Author SunMeng
 * Date : 2015 八月 09
 */
public class YummyWakeUpApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        RunTime.init(this);
    }
}
