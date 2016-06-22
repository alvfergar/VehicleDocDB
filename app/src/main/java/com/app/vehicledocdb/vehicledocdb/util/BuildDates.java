package com.app.vehicledocdb.vehicledocdb.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Alvaro on 22/06/2016.
 */
public class BuildDates {

    public static String convertYearMonthDayToDayMonthYear(String date) {
        SimpleDateFormat dateView;
        SimpleDateFormat datePersist;
        Calendar cal = Calendar.getInstance();
        String result;

        datePersist = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        dateView = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        try {
            cal.setTime(datePersist.parse(date));
        } catch (ParseException e) {
            Log.e("Date", "Date conversion error");
            e.printStackTrace();
        }

        result = dateView.format(cal.getTime());

        return result;
    }

    public static String convertDayMonthYearToYearMonthDay(String date) {
        SimpleDateFormat dateView;
        SimpleDateFormat datePersist;
        Calendar cal = Calendar.getInstance();
        String result;

        datePersist = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        dateView = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        try {
            cal.setTime(dateView.parse(date));
        } catch (ParseException e) {
            Log.e("Date", "Date conversion error");
            e.printStackTrace();
        }

        result = datePersist.format(cal.getTime());

        return result;
    }
}
