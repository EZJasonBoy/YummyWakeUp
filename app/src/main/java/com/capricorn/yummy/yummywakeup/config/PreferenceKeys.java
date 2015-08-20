package com.capricorn.yummy.yummywakeup.config;

/**
 * SharedPreference to save alarm ID which will be used to search alarm
 * in SQLite
 */
public class PreferenceKeys {

    // Share preference name
    public final static String SHARE_PREF_NAME = "yummywakeup_share_preference";
    // ID to save current alarm
    public final static String KEY_ALARM_ID = "alarm_id";
    public final static String KEY_ALARM_TIME = "alarm_time";

    public static final String KEY_VOLUME_BEHAVIOR = "alarm_volume";
    public static final String KEY_ALARM_SNOOZE = "alarm_snooze";

}
