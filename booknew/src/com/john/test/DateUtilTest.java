package com.john.test;

import com.john.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author John
 * @create 2021-10-109:07
 */
public class DateUtilTest {

    @Test
    public void DBDateToJavaDate() {
    }

    @Test
    public void javaDateToDBDate() {
    }

    @Test
    public void dateToString() {
        Date date = new Date();
        System.out.println(date.getClass().getName());
        System.out.println(date);
        String time = DateUtil.DateToString(date);
        System.out.println(time.getClass().getName());
        System.out.println(time);
    }

    @Test
    public void stringToDate() throws ParseException {
        String time = "2021-10-10 09:10:03";
        Date date = DateUtil.StringToDate(time);
        System.out.println(date.getClass().getName());
        System.out.println(date);
    }
}