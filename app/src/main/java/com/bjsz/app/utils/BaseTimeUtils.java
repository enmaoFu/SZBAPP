package com.bjsz.app.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @description  时间工具类
 * @author enmaoFu
 * @date 2016-12-23
 */
public class BaseTimeUtils {

	private static String format = "yyyy-MM-dd HH:mm:ss";

	/** 时间格式 */
	/**
	 * 获取当前系统时间 格式为yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		String currentTime = sdf.format(date);
		return currentTime;
	}

	/**
	 * 字符串转化为时间格式的date对象 格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @param time
	 * @return
	 */
	public static Date stringcgdate(String time) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = df.parse(time);
		} catch (Exception e) {

		}
		return date;
	}

	/**
	 * 时间Date格式对象转化为字符串对象 格式：yyyy-MM-dd
	 *
	 * @param date
	 * @return
	 */
	public static String datecgstring(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年 MM月dd日");
		if (date == null) {
			return "";
		} else {
			return df.format(date);
		}
	}

	/**
	 * 获取当天开始时间 返回yyyy-MM-dd HH:mm:ss时间格式对象
	 *
	 * @return
	 */
	public static String getdaystart() {
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		Date date = (Date) currentDate.getTime().clone();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * 与当前时间的比较
	 *
	 * @param oldtime
	 *            原来时间
	 * @return
	 */
	public static long compare(String oldtime) {
		Date olddateDate = null, nowdateDate = null;
		try {
			olddateDate = ConverToDate(oldtime);
			nowdateDate = ConverToDate(getCurrentTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (olddateDate == null) {
			return -36000000;
		} else {
			return nowdateDate.getTime() - olddateDate.getTime();
		}
	}

	// 把字符串转为日期
	public static Date ConverToDate(String strDate) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(strDate);
	}
}
