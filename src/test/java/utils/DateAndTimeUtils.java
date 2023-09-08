package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateAndTimeUtils {


    // Get the current date and time as a formatted string
    public static String getCurrentDateTime(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

    // Get the current date and time as a formatted string using a default format
    public static String getCurrentDateTime() {
        return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
    }

    // Add or subtract days to/from a given date
    public static String addDaysToCurrentDate(String format, int days) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(java.util.Calendar.DATE, days);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(calendar.getTime());
    }

    // Add or subtract days to/from the current date using a default format
    public static String addDaysToCurrentDate(int days) {
        return addDaysToCurrentDate("yyyy-MM-dd HH:mm:ss", days);
    }
}


