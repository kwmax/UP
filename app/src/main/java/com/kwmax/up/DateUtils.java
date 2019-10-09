package com.kwmax.up;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kwmax on 2019/5/27.
 */
public class DateUtils {

    public static String getCurrentDate(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }

    public static String getCurrentDateWithFormat(String format){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }

    /**
     *
     * @param datestr 2019-5-28
     * @return 2019-5-29
     */
    public static String getNextDay(String datestr){

        try {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(datestr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 1);// 加10天
            date = calendar.getTime();
            String nextday = sdf.format(date);
            return nextday;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }


    }
}
