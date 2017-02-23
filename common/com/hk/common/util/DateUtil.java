package com.hk.common.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.web.servlet.support.RequestContext;


public class DateUtil {
private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
private static final String INDO_DATE_FORMAT = "dd-MM-yyyy";
private static final String INDO_DATE_FORMAT_SLASH = "dd/MM/yyyy";
private static final String DEFAULT_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
private static final String VERSION_DATE_TIME_FORMAT = "ddMMyyyyHHmmss";
private static final String DEFAULT_TIME_FORMAT = "HH:mm";
private static final String DEFAULT_DATE_DETAIL_MONTH = "dd MMMM yyyy";
private static final String DEFAULT_DATETIME_DETAIL_MONTH = "dd MMMM yyyy HH:mm:ss";
	
	public static synchronized String defaultFormatDatetime(Date date){
		return new SimpleDateFormat(DEFAULT_DATETIME_FORMAT).format(date);
	}

	public static synchronized String defaultFormatDate(Date date){
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
	}
	
	public static synchronized String indoFormatDate(Date date){
		return new SimpleDateFormat(INDO_DATE_FORMAT).format(date);
	}
	
	public static synchronized String indoFormatDateSlash(Date date){
		return new SimpleDateFormat(INDO_DATE_FORMAT_SLASH).format(date);
	}
	
	public static synchronized String formatDateTime(Date date){
		return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).format(date);
	}
	
	public static synchronized String dateDetailMonth(Date date){
		return new SimpleDateFormat(DEFAULT_DATE_DETAIL_MONTH).format(date);
	}
	
	public static synchronized String dateTimeDetailMonth(Date date){
		return new SimpleDateFormat(DEFAULT_DATETIME_DETAIL_MONTH).format(date);
	}
	
	public static synchronized String defaultTimeFormat(Date date){
		return new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(date);
	}
	
	public static synchronized Date now(){
		return new Date();
	}
	
	public static synchronized String nowFormatted(){
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(now());
	}
	
	public static synchronized String nowVersion(){
		return new SimpleDateFormat(VERSION_DATE_TIME_FORMAT).format(now());
	}
	
	public static synchronized String nowFormatted(String format){
		return new SimpleDateFormat(format).format(now());
	}
	
	public static synchronized Date toDate(String dateString){
		Date result = null;

		if (dateString != null && !dateString.equals(""))
		{
			try {
				result = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public static synchronized Date toDateAndTime(String dateString){
		Date result = null;

		if (dateString != null && !dateString.equals(""))
		{
			try {
				result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public static synchronized Date toDateFromIndoFormat(String dateString){
		Date result = null;

		if (dateString != null && !dateString.equals(""))
		{
			try {
				result = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public static synchronized Date toDateTime(String date){
		Date result = null;

		if (date != null && !date.equals(""))
		{
			String x1[] = date.split(" ");
			String dateSplit = x1[0];
			String timeSplit = x1[1];
			String x2[] = dateSplit.split("-");
			String x3[] = timeSplit.split(":");
			if (x2.length == 3)
			{
				DateTime dt = new DateTime(Integer.parseInt(x2[0]), Integer.parseInt(x2[1]), Integer.parseInt(x2[2]), Integer.parseInt(x3[0]), Integer.parseInt(x3[1]), 0, 0);
				result = dt.toDate();
			}
		}

		return result;
	}
	
	public static synchronized Date toDateOrDateTime(String date){
		Date result = null;

		if (date != null && !date.equals(""))
		{
			String x1[] = date.split(" ");
			if( x1.length == 2 ){
				String dateSplit = x1[0];
				String timeSplit = x1[1];
				String x2[] = dateSplit.split("-");
				String x3[] = timeSplit.split(":");
				if (x2.length == 3)
				{
					DateTime dt = new DateTime(Integer.parseInt(x2[0]), Integer.parseInt(x2[1]), Integer.parseInt(x2[2]), Integer.parseInt(x3[0]), Integer.parseInt(x3[1]), 0, 0);
					result = dt.toDate();
				}
			}else{
				String x[] = date.split("-");
				if (x.length == 3)
				{
					DateTime dt = new DateTime(Integer.parseInt(x[0]), Integer.parseInt(x[1]), Integer.parseInt(x[2]), 0, 0, 0, 0);
					result = dt.toDate();
				}
			}

		}

		return result;
	}
	
	public static synchronized int[] toDateTimeSplit(String date){
		int[] result = new int[5];

		String x1[] = date.split(" ");
		if( x1.length == 2 ){
			String dateSplit = x1[0];
			String timeSplit = x1[1];
			String x2[] = dateSplit.split("-");
			String x3[] = timeSplit.split(":");
			result[0] = Integer.parseInt(x2[0]);
			result[1] = Integer.parseInt(x2[1]);
			result[2] = Integer.parseInt(x2[2]);
			result[3] = Integer.parseInt(x3[0]);
			result[4] = Integer.parseInt(x3[1]);
			
		}else{
			
			String dateSplit = x1[0];
			String x2[] = dateSplit.split("-");
			result[0] = Integer.parseInt(x2[0]);
			result[1] = Integer.parseInt(x2[1]);
			result[2] = Integer.parseInt(x2[2]);
			result[3] = 0;
			result[4] = 0;

		}
		
		return result;
	}
	
	public static synchronized String getCurrentMonthString(){
		return new SimpleDateFormat("MMMMM").format(now());
	}
	
	public static synchronized int getCurrentDate(){
		return new DateTime().getDayOfMonth();
	}

	public static synchronized int getCurrentMonth(){
		return new DateTime().getMonthOfYear();
	}

	public static synchronized int getCurrentYear(){
		return new DateTime().getYear(); 
	}
	
	@SuppressWarnings("deprecation")
	public static synchronized Time toTime(String time){
		Time result = null;
		
		if(time != null && !time.equals("")){
			String x[] = time.split(" ");
			String completeHour = x[0]; //hour
			String amPm = x[1]; //amPm

			String[] splittedHour = completeHour.split(":");
			
			int hour = Integer.parseInt(splittedHour[0]);
			int minute = Integer.parseInt(splittedHour[1]);
			int second = 0;
			
			if(amPm.equalsIgnoreCase("PM")){
				hour += 12;
			}
			
			result = new Time(hour, minute, second);
		}
		
		return result;
	}
	
	public static synchronized Map<Integer, String> getDates(){
		Map<Integer, String> dates = new HashMap<Integer, String>();
		
		for(int d = 1; d <=31; d++){
			dates.put(d, String.valueOf(d));
		}
		
		return dates;
	}
	
	public static synchronized Map<Integer, String> getMonths(HttpServletRequest request){
		RequestContext ctx = new RequestContext(request);
		Map<Integer, String> months = new HashMap<Integer, String>();
		
		months.put(1, ctx.getMessage("whm.common.months.january"));
		months.put(2, ctx.getMessage("whm.common.months.february"));
		months.put(3, ctx.getMessage("whm.common.months.march"));
		months.put(4, ctx.getMessage("whm.common.months.april"));
		months.put(5, ctx.getMessage("whm.common.months.may"));
		months.put(6, ctx.getMessage("whm.common.months.june"));
		months.put(7, ctx.getMessage("whm.common.months.july"));
		months.put(8, ctx.getMessage("whm.common.months.august"));
		months.put(9, ctx.getMessage("whm.common.months.september"));
		months.put(10, ctx.getMessage("whm.common.months.october"));
		months.put(11, ctx.getMessage("whm.common.months.november"));
		months.put(12, ctx.getMessage("whm.common.months.december"));
		
		return months;
	}
	
	public static synchronized Map<Integer, Integer> getYears(){
		Map<Integer, Integer> years = new TreeMap<Integer, Integer>();

		for(int y = 1922; y <= new DateTime().getYear(); y++){
			years.put(y, y);
		}
		
		return years;
	}	
	
	public static final synchronized Date toEndDate(Date date)
    {
        DateTime dateTime = new DateTime(date);
        return new DateTime(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.dayOfMonth().getMaximumValue(),0,0,0,0).toDate();
    }
    
    public static final synchronized Date toStartDate(Date date)
    {
        DateTime dateTime = new DateTime(date);
        return new DateTime(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.dayOfMonth().getMinimumValue(),0,0,0,0).toDate();
    }
    
    public static final synchronized Date toEndDateInOneYear(Date date)
    {
        DateTime dateTime = new DateTime(date);
		DateTime dt = new DateTime(dateTime.getYear(), dateTime.monthOfYear().getMaximumValue(), dateTime.dayOfMonth().getMaximumValue(),0,0,0,0);
        return new DateTime(dt.getYear(), dt.monthOfYear().getMaximumValue(), dt.dayOfMonth().getMaximumValue(),0,0,0,0).toDate();
    }
    
    public static final synchronized Date toStartDateInOneYear(Date date)
    {
        DateTime dateTime = new DateTime(date);
        return new DateTime(dateTime.getYear(), dateTime.monthOfYear().getMinimumValue(), dateTime.dayOfMonth().getMinimumValue(),0,0,0,0).toDate();
    }
    
    public static final synchronized Integer getYearFromDate(Date date)
    {
    	    Calendar cal = Calendar.getInstance();
    	    cal.setTime(date);
    	    int year = cal.get(Calendar.YEAR);
    	    
    	    return year;
    }
    
    public static final synchronized Integer getMonthFromDate(Date date)
    {
    	    Calendar cal = Calendar.getInstance();
    	    cal.setTime(date);
    	    int year = cal.get(Calendar.MONTH);
    	    
    	    return year+1;
    }
    
    public static final synchronized String getFirstDateOfCurrentMonth(){
    	Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		String startDate = df.format(date.getTime());

		return startDate;
    }
    
    public static final synchronized String getLastDateOfCurrentMonth(){
    	Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		
		return sdf.format(lastDayOfMonth);
    }
    
    public static final synchronized String getYesterdayDate(){
    	Calendar date = Calendar.getInstance();
    	date.add(Calendar.DATE, -1);
		DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		String startDate = df.format(date.getTime());

		return startDate;
    }
    
    public static synchronized String FromStringSqlDate(String date, String format) throws ParseException{
    	Date newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		return new SimpleDateFormat(format).format(newDate);
	}
    
    public static synchronized String FromDateStringToString(String date, String format) throws ParseException{
    	Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		return new SimpleDateFormat(format).format(newDate);
	}
    
    public static synchronized int daysBetween(long d1, long d2) {
        return (int) ((d1 - d2) / (1000 * 60 * 60 * 24));
    } 
   
}
