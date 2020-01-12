package com.example.summer.utility.dataUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InitTimeUtility {
    private static final String FORMAT = "yyyy-MM-dd_HH-mm-ss-SSS";
    public static String getCurrentTime(){
        Calendar cal=Calendar.getInstance();
        String currentTime=(new SimpleDateFormat(FORMAT)).format(cal.getTime());
        return currentTime;
        }
}
