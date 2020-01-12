package com.example.summer.utility.dateUtility;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateUtility {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter ID_FORMAT
            = DateTimeFormat.forPattern("yyyy-MM-dd_HH-mm-ss-SSS");

    public static String convertIdToDate(String id){
        DateTime dateTime = DateTime.parse(id, ID_FORMAT);
        return dateTime.toString(DATE_FORMAT);
    }
}
