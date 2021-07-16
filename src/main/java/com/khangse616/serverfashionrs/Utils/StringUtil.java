package com.khangse616.serverfashionrs.Utils;

import org.apache.commons.lang3.ArrayUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // This method compares two strings
    // lexicographically without using
    // library functions
    public static int stringCompare(String str1, String str2) {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int) str1.charAt(i);
            int str2_ch = (int) str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }

    public static boolean checkStringIsNumeric(String number) {
        if (number == null) {
            return false;
        }
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static String convertTimestampToString(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    public static String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static Date convertStringToDate(String dateStr, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateStr);
    }

    public static String[] removeElementInArray(String[] arrString, int index) {

        if (arrString == null || index < 0 || index > arrString.length) {

            return arrString;
        }
        // Create a proxy array of size one less than original array
        String[] proxyArray = new String[arrString.length - index];

        // copy all the elements in the original to proxy array except the one at index
        for (int i = 0, k = 0; i < arrString.length; i++){

            // check if index is crossed, continue without copying
            if (i == index) {
                continue;
            }

            // else copy the element
            proxyArray[k++] = arrString[i];
        }

        return proxyArray;
    }

    public static String[] removeElementInArray(String[] arrString, int[] indexes) {

        if (arrString == null || indexes.length == 0 ) {

            return arrString;
        }
        // Create a proxy array of size one less than original array
        String[] proxyArray = new String[arrString.length - indexes.length];

        // copy all the elements in the original to proxy array except the one at index
        for (int i = 0, k = 0; i < arrString.length; i++){

            // check if index is crossed, continue without copying
            if (ArrayUtils.contains(indexes, i)) {
                continue;
            }

            // else copy the element
            proxyArray[k++] = arrString[i];
        }

        return proxyArray;
    }
}
