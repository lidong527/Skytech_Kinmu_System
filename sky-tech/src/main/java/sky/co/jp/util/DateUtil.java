package sky.co.jp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
   private DateUtil(){};
    public static String getThisMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth; }

    public static String getCurrentDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("dd");
        String currentDay = dft.format(cal.getTime());
        return currentDay; }

    //取当前的业务月份
    public static Date getGyomuMonth() {
    	Date GyomuMonth=new Date();
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        //何月の勤務表を判断する
        if(day > 16){
        	//今月の第一日を設定する
            cal.set(Calendar.DAY_OF_MONTH, 1);
        }else{
            //来月の月数を設定する
            cal.add(Calendar.MONTH, -1);
            //来月の第一日を設定する
            cal.set(Calendar.DAY_OF_MONTH, 1);
        }
        try {
        	GyomuMonth =dft.parse(dft.format(cal.getTime()));
        }
        catch(Exception e){}
        return GyomuMonth;
    }
}
