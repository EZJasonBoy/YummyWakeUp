package com.capricorn.yummy.yummywakeup.model;

import java.util.Calendar;

/**
 * Model to present current time
 * Created by Chuan on 8/4/2015.
 */
public class Time {

    private int hour;
    private int min;
    private int month;
    private int weekDay;
    private int day;
    private String timeLabel = "";         // Current Hour:Min shown on TextView tv_curentTime
    private String weekMonthDayLabel = ""; // Current Week, Month Day shown on TextView tv_week_month_day
    private Calendar date;

    public Time(long time) {

        date = Calendar.getInstance();
        date.setTimeInMillis(time);

        this.month = date.get(Calendar.MONTH);
        this.day = date.get(Calendar.DAY_OF_MONTH);
        this.weekDay = date.get(Calendar.DAY_OF_WEEK);
        this.hour = date.get(Calendar.HOUR_OF_DAY);
        this.min = date.get(Calendar.MINUTE);

        this.timeLabel = TimeToString();
        this.weekMonthDayLabel = WeekDayToString() + ", " + MonthToString() + " " + this.day;

    }

    /**
     * Return a string to present current hour and min
     * @return String of current hour and min
     */
    private String TimeToString() {
        return this.hour + ":" + this.min;
    }

    /**
     * Return a string to present current month (ex: 1 -> Jan)
     * @return String corresponding current month
     */
    private String MonthToString() {
        String month = "";
        switch (this.month) {
            case Calendar.JANUARY:
                month = "January";
                break;
            case Calendar.FEBRUARY:
                month = "February";
                break;
            case Calendar.MARCH:
                month = "March";
                break;
            case Calendar.APRIL:
                month = "April";
                break;
            case Calendar.MAY:
                month = "May";
                break;
            case Calendar.JUNE:
                month = "June";
                break;
            case Calendar.JULY:
                month = "July";
                break;
            case Calendar.AUGUST:
                month = "August";
                break;
            case Calendar.SEPTEMBER:
                month = "September";
                break;
            case Calendar.OCTOBER:
                month = "October";
                break;
            case Calendar.NOVEMBER:
                month = "November";
                break;
            case Calendar.DECEMBER:
                month = "December";
                break;
        }
        return month;
    }

    /**
     * Return a string to present current month (ex: 1 -> Mon)
     * @return String corresponding current week day
     */
    private String WeekDayToString() {
        String weekDay = "";
        switch (this.weekDay) {
            case Calendar.MONDAY:
                weekDay = "Mon";
                break;
            case Calendar.TUESDAY:
                weekDay = "Tue";
                break;
            case Calendar.WEDNESDAY:
                weekDay = "Wed";
                break;
            case Calendar.THURSDAY:
                weekDay = "Thu";
                break;
            case Calendar.FRIDAY:
                weekDay = "Fri";
                break;
            case Calendar.SATURDAY:
                weekDay = "Sat";
                break;
            case Calendar.SUNDAY:
                weekDay = "Sun";
                break;
        }
        return weekDay;
    }

    public String getTimeLabel() {
        return timeLabel;
    }

    public String getWeekMonthDayLabel() {
        return weekMonthDayLabel;
    }
}
