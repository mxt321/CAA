package net.bjyfkj.caa.util;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**一些数据转换的操作*/
public class DataUtil {
	

	/**
	 * 将dp转换为px
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px转换为dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}


	/**
	 * 将px值转换为sp值，保证文字大小不变
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}


	/***
	 * 以年-月-日形式返回当前的时间
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * 将 unicode 转字符串
	 */
	public static String unicode2String(String unicode) {

		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while ((i = unicode.indexOf("\\u", pos)) != -1) {
			sb.append(unicode.substring(pos, i));
			if (i + 5 < unicode.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(
						unicode.substring(i + 2, i + 6), 16));
			}
		}

		return sb.toString();

	}

	/**将yyyy-MM-dd HH:mm:ss格式的字符串转换为日期对象*/
	public static Calendar getDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		
		try {
			c.setTime(sdf.parse(str));
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return c;
		}
	}
	
	/**将毫秒数转换为yyyy-MM-dd HH:mm:ss格式日期字符串*/
	public static String getData(long milliseconds){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(milliseconds));
	}


}
