package com.cam.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月3日 下午7:47:27
 *
 * 功能描述： Date工具类
 * 
 * 版本： V1.0
 */
public class DateUtil {
	/**
	 * 功能描述: 获取两个日期中间的秒数
	 * @return long
	 */
	public static long getSecondsBetweenDates(Date d1, Date d2) {
		return Math.abs((d1.getTime() - d2.getTime()) / 1000);
	}

	/**
	 * 功能描述: 获取某一天的最后一分钟 23:59:59
	 * @return Date
	 */
	public static Date endOfDay(Date d) {
		return DateUtils.addSeconds(DateUtils.addDays(DateUtils.truncate(d, Calendar.DATE), 1), -1);
	}
}
