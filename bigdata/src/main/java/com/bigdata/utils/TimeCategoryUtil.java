package com.bigdata.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCategoryUtil {
    private static final long initDate = 1199120400000L;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");

    public TimeCategoryUtil() {
    }

    public long translateTimeToLong(String date) {
        long time = 0L;

        try {
            time = this.sdf.parse(date).getTime();
        } catch (ParseException var5) {
            var5.printStackTrace();
            time = 1199120400000L;
        }

        return time - 1199120400000L;
    }

    public Date getDateFromLong() {
        Date date1 = new Date(1199120400000L);
        System.out.println(date1);
        return null;
    }
}
