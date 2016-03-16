
package com.test1.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jasswin
 *
 */
public class DateUtils {

	private final static Logger LOG = LoggerFactory.getLogger(DateUtils.class);
	
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}

	public static int getMonthFromOne(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		return c.get(Calendar.MONTH);
	}
	
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static Date addDay(Date date, int increment) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, increment);
		return c.getTime();
	}
	
	public static Date addMonth(Date date,int increment) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, increment);
		return c.getTime();
	}
	
	public static Date addHourOfDay(Date date,int increment) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, increment);
		return c.getTime();
	}
	
	public static Date addHour(Date date,int increment) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, increment);
		return c.getTime();
	}
	
	/**
	 * 获取该日期是该年的第几周，返回�?�?�?��
	 * @author Light
	 * @date 2015-9-22 14:57:22 
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(1);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 跨年周问�?跨年账单 
	 * @author Light
	 * @date 2016�?�?�?5:30:22 
	 */
	public static int getCrossWeek(Date date){
		int month = DateUtils.getMonth(date)+1;
		int week = DateUtils.getWeekOfYear(date);
		if(week == 1 && month == 12){
			week = 53;
		}
		return week;
	}
	
	public static List<Date> findDates(String start, String end) {
		List<Date> lDate = new ArrayList<Date>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dBegin = sdf.parse(start);
			Date dEnd = sdf.parse(end);			
			lDate.add(dBegin);
			Calendar calBegin = Calendar.getInstance();
			// 使用给定�?Date 设置�?Calendar 的时�?
			calBegin.setTime(dBegin);
			Calendar calEnd = Calendar.getInstance();
			// 使用给定�?Date 设置�?Calendar 的时�?
			calEnd.setTime(dEnd);
			// 测试此日期是否在指定日期之后
			while (dEnd.after(calBegin.getTime())) {
				// 根据日历的规则，为给定的日历字段添加或减去指定的时间�?
				calBegin.add(Calendar.DAY_OF_MONTH, 1);
				lDate.add(calBegin.getTime());
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lDate;
	}

	public static String toString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String formateDate(String time, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(Timestamp.valueOf(time));
	}

	public static String formateShortDate(String time) {
		return formateDate(time, "yyyy-MM-dd");
	}

	public static String formateDateTime(String time) {
		return formateDate(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static Timestamp getTimestamp() {
		Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());
		return time;
	}

	/**
	 * 比较两个时间差是否在范围�?
	 * 
	 * @param date1
	 *            较晚时间
	 * @param date2
	 *            较早时间
	 * @param minute
	 *            差�?范围
	 * @return 如在差�?范围内返回true,否则返回False. 如date1<date2返回false.
	 */
	public static boolean compareDiff(Date date1, Date date2, int minute) {
		if (!date1.before(date2)) {
			long diff = date1.getTime() - date2.getTime();
			if (diff / 60000 < minute) {
				return true;
			}
		}
		return false;
	}

	/***
	 * 
	 * @param date
	 * @return @tale:
	 * @purpose：格式化时间
	 * @author：Simon - 赵振�?
	 * @CreationTime：Aug 10, 20102:25:34 PM
	 */
	public static Date getDateTimeByhhmmss(String date) {
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = null;
		try {
			time = sdt.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("输入时间格式不对");
		}
		return time;
	}

	/***
	 * 
	 * @param date
	 * @return @tale:
	 * @purpose：格式化时间
	 * @author：Simon - 赵振�?
	 * @CreationTime：Aug 10, 20102:25:34 PM
	 */
	public static Date getDateTimeByymd(String date) {
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		Date time = null;
		try {
			time = sdt.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("输入时间格式不对");
		}
		return time;
	}

	/**
	 * 两个日期做减法，返回相差天数
	 * 
	 * @throws ParseException
	 * @throws ParseException
	 */
	public static long datesub(Date date1, Date date2) throws ParseException {
		@SuppressWarnings("unused")
		long l = date1.getTime() - date2.getTime() > 0 ? date1.getTime()
				- date2.getTime() : date2.getTime() - date1.getTime();
		// 日期相减得到相差的日�?
		long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ? (date1
				.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)
				: (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
		return day + 1;
	}

	public static String getNewDate() {
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time;
		time = sdt.format(new Date());

		return time;

	}

	public static String getBefoDateShort(String date, int i){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			 d= f.parse(date);
		} catch (Exception e) {
			LOG.error("str date = " + d + ", format = " + f, e);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c = getBeforCaleander(c, i);
		return f.format(c.getTime());
	}

	private static Calendar getBeforCaleander(Calendar c, int day) {
		c.add(Calendar.DATE, day);
		return c;
	}

	public static String getShortNow() {
		// TODO Auto-generated method stub
		String time = getNewDate();
		return formateDate(time, "yyyy-MM-dd");
	}

	public static String formatShortDate(Date time) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formateDate(sdt.format(time), "yyyy-MM-dd");
	}

	public static String formatLongDate(Date time) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdt.format(time).toString();
	}

	public static String getNow() {
		// TODO Auto-generated method stub
		String time = getNewDate();
		return formateDate(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getFirstDayOfWeek(int year, int week) {

		Calendar c = Calendar.getInstance();
		if (year == 0) {
			year = c.get(Calendar.YEAR);// �?
			// year=new Date().getYear();
		}
		if (week == 0) {
			week = c.get(Calendar.WEEK_OF_YEAR);
		}
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		setBeginTime(c);
		return c.getTime();
	}

	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = Calendar.getInstance();
		if (year == 0) {

			year = c.get(Calendar.YEAR);// �?
		}
		if (week == 0) {
			week = c.get(Calendar.WEEK_OF_YEAR);
		}
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		setEndTime(c);
		return c.getTime();
	}

	public static void setBeginTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		setBeginTime(c);
		date.setTime(c.getTimeInMillis());
	}

	public static void setEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		setEndTime(c);
		date.setTime(c.getTimeInMillis());
	}

	private static void setBeginTime(Calendar c) {
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
	}

	private static void setEndTime(Calendar c) {
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
	}
	
	public static void setHourBeginTime(Calendar c) {
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
	}

	public static void setHourEndTime(Calendar c) {
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
	}

	public static void main(String[] args) {
		System.out.println("***************");
		//跨年周问�?
		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-21")));
		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-22")));
		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-23")));
		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-24")));
		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-25")));
		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-26")));
		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-27")));
		System.out.println("=======");
//		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-28")));
//		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-29")));
//		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-30")));
//		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2015-12-31")));
//		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2016-1-1")));
//		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2016-1-2")));
//		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2016-1-3")));
		
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2010-12-28")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2011-12-29")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2012-12-30")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2013-12-31")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2014-12-31")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2009-12-31")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2008-12-31")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2007-12-31")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2006-1-1")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2016-1-2")));
		System.out.println(DateUtils.getCrossWeek(getDateTimeByymd("2016-1-3")));
		
		System.out.println("------");
		System.out.println(DateUtils.getWeekOfYear(getDateTimeByymd("2016-1-4")));
		
		
		
		System.out.println(getBefoDateShort("2015-10-18 00:51:11",1));
		String d = DateUtils.formatToString(DateUtils.addHourOfDay(new Date(), -1),"yyyy-MM-dd HH");
		System.out.println("-----------------------------------------------"+ d);
		System.out.println("2015-10-18".substring(5, 7));
/*		String format = "yyyy-MM-dd HH";
		
		Date now = new Date();
		Date lastHour = addHourOfDay(now, -1);
		System.out.println("当前时间�? + formatToString(now,format));
		System.out.println("前一个小时时间：" + formatToString(lastHour,format));
		String lastHourStr = formatToString(lastHour,format);
		String startTime = lastHourStr + ":00:00";
		String endTime = lastHourStr + ":59:59";
		System.out.println(startTime);
		System.out.println(endTime);*/
		
		System.out.println("-----------------------------------------------"+d.substring(0,10).trim());
		System.out.println("-----------------------------------------------"+d.substring(11,13).trim());
		
/*		String format = "yyyy-MM-dd HH:mm:ss";
		
		Date now = new Date();
		Date lastHour = addHourOfDay(now, -1);
		System.out.println("当前时间�? + formatToString(now,format));
		System.out.println("前一个小时时间：" + formatToString(lastHour,format));
		
		Calendar c = Calendar.getInstance();
		c.setTime(lastHour);
		setHourBeginTime(c);
		lastHour.setTime(c.getTimeInMillis());
		System.out.println("�?��时间�?+formatToString(lastHour,format));
		
		setHourEndTime(c);
		lastHour.setTime(c.getTimeInMillis());
		System.out.println("结束时间�?+formatToString(lastHour,format));*/
		
		
		
//		Date c = new Date();
//		String format = "yyyy-MM-dd";
//		System.out.println(formatToString(c, format));
//		
//		System.out.println(formatToString(addDay(c, 1), format));
//		System.out.println(formatToString(addDay(c, 32), format));
//		System.out.println(formatToString(addDay(c, -1), format));
//		System.out.println(formatToString(addDay(c, -32), format));
	}

	public static Date parseToDate(String dataStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dataStr);
		} catch (ParseException e) {
			LOG.error("str date = " + dataStr + ", format = " + format, e);
		}
		return null;
	}

	public static String formatToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
