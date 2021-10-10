package com.john.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * @author John
 * @create 2021-10-108:37
 */
public class DateUtil {
    public static  java.util.Date DBDateToJavaDate(java.sql.Timestamp time) {
        if (time == null){
            return null;
        }
        return new java.util.Date(time.getTime());
    }

    public static java.sql.Timestamp JavaDateToDBDate(java.util.Date time){
        if (time == null){
            return null;
        }
        return new java.sql.Timestamp(time.getTime());
    }

    public static String DateToString(java.util.Date time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(time);
        return format;
    }

    public static Date StringToDate(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = sdf.parse(time);
        return date;
    }
}

