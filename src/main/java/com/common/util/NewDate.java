package com.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author zhaomy07
 * @date 2017年10月10日 上午10:23:44
 * @version V1.0.0
 * description：
 */
public class NewDate {

	public static String getYear(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Timestamp time = new Timestamp(new Date().getTime());
		return sdf.format(time);
	}
	
	public static String getMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		Timestamp time = new Timestamp(new Date().getTime());
		return sdf.format(time);
	}

	public static String getYm(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Timestamp time = new Timestamp(new Date().getTime());
		return sdf.format(time);
	}
	
	public static String getYmd(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp time = new Timestamp(new Date().getTime());
		return sdf.format(time);
	}
	
	public static String getYmdhm(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Timestamp time = new Timestamp(new Date().getTime());
		return sdf.format(time);
	}

	public static String getDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time = new Timestamp(new Date().getTime());
		return sdf.format(time);
	}

	public static String getDayTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		Timestamp time = new Timestamp(new Date().getTime());
		return sdf.format(time);
	}
	
	/**
	 * 把小时往前减少一小时，为负数,若想把时期向后推一小时则将改为正数
	 * @return
	 */
	public static String getNHour(int i){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, i);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 把日期往前减少一天，为负数,若想把日期向后推一天则将改为正数
	 * @return
	 */
	public static String getNDate(int i){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, i);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 把月期往前减少一月，为负数,若想把月期向后推一月则将改为正数
	 * @return
	 */
	public static String getNMonth(int i){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, i);
		return sdf.format(calendar.getTime());
	}
	
	public static long getLongTime(String time) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(time);
		long longTime = date.getTime();
		return longTime;
	}
	
	public static String getFormatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat(format);
        return sdFormat.format(date);
    }

	/** 
     * 两个时间相差距离多少秒 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return
     */  
    public static long getDistanceTimes(String str1, String str2) {  
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date one;  
        Date two;  
        long sec = 0; 
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1 < time2) {  
                diff = time2 - time1;  
            } else {  
                return 0L;  
            }  
            sec = diff / 1000;  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return sec;  
    } 
	
    /** 
     * 获取精确到秒的时间戳 (往后+N分钟)
     * @param date 
     * @return 
     */  
    public static Long getSecondTimestamp(int minutes){  
    	long current = System.currentTimeMillis();
    	current += minutes * 60 * 1000;
    	Date date = new Date(current);
        String timestamp = String.valueOf(date.getTime()/1000);  
        return Long.valueOf(timestamp);  
    }
	
	public static void main(String[] args) {
		/*System.out.println(getSecondTimestamp(0));*/
		/*System.out.println(getDate() + " 23:59:59");
		System.out.println(NewDate.getNDate(-2) + " 00:00:00");*/
		/*System.out.println("0="+System.currentTimeMillis());
		String a = getYmdhm();
		System.out.println("a="+a);
		try {
			long b = getLongTime(a);
			System.out.println("b="+b);
		} catch (ParseException e) {
		}*/
		//System.out.println(getDistanceTimes("2018-01-18 12:00:00", "2018-01-18 13:00:02"));
		System.out.println(getDayTime());
		System.out.println(NewDate.getNDate(-5) + " 00:00:00");
	}
	
}
