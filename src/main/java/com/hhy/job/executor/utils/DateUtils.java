package com.hhy.job.executor.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * 毫秒转化为正常时间格式
     *
     * @param ms 毫秒数
     * @return 时间格式的字符串
     */
    public static String timeFormat(long ms) {
        Date date = new Date(ms);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 将时间字符串转为时间
     * @param dateStr 时间字符串
     * @param format 格式
     * @return 时间
     * @throws ParseException
     */
    public static Date parse(String dateStr,String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateStr);
    }

    /**
     * 时间格式化为正常时间格式
     *
     * @param date
     * @return
     */
    public static String timeFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String timeFormat(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 正常时间为时间格式化格式
     *
     * @param date
     * @return
     */
    public static Date timeFormatToDate(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 正常时间为时间格式化格式
     *
     * @param date
     * @return
     */
    public static String timeFormatToDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间格式转化为 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 计算两个时间的时间差
     */
    public static String getDatePoor(Date endDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - new Date().getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 单纯计算两个时间相差多少分钟
     *
     * @param endDate   结束时间
     * @param beginDate 开始时间
     * @return
     */
    public static long getDateMinPoor(Date beginDate, Date endDate) {
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - beginDate.getTime();
        // 计算差多少分钟
        long min = diff / nm;
        return min;
    }

    /**
     * 根据毫秒数据返回天时分秒
     *
     * @param time
     * @return
     */
    public static String getDHMSByTime(long time) {
        Long d = time / (1000 * 60 * 60 * 24);
        Long h = (time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        Long m = (time % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60);
        Long s = (time % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) % (1000 * 60) / 1000;
        return d + "天" + h + "时" + m + "分" + s + "秒";
    }

    public static String dateFormat(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取时间段内月列表
     *
     * @param minDate
     * @param maxDate
     * @return
     */
    public static int getMonthBetweenDates(String minDate, String maxDate) {
        ArrayList<String> result = new ArrayList<>();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(timeFormatToDate(minDate, "yyyy-MM"));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.setTime(timeFormatToDate(maxDate, "yyyy-MM"));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(dateFormat(curr.getTime(), "yyyy-MM"));
            curr.add(Calendar.MONTH, 1);
        }
        return result.size();
    }

    /**
     * 获取上个月最后一秒
     *
     * @param date
     * @return
     */
    public static Date getMonthEndTime(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        cale.set(Calendar.MILLISECOND, 999);
        return cale.getTime();
    }

    /**
     * 获取上个月最后一秒
     *
     * @param date
     * @return
     */
    public static String getMonthStartDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, -1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        cale.set(Calendar.MILLISECOND, 999);
        return timeFormat(cale.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取当月第一秒
     *
     * @param date
     * @return
     */
    public static Date getThisMonthStartDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.set(Calendar.HOUR_OF_DAY, 00);
        cale.set(Calendar.MINUTE, 00);
        cale.set(Calendar.SECOND, 00);
        cale.set(Calendar.MILLISECOND, 000);
        return cale.getTime();
    }

    /**
     * 获取当月最后秒
     *
     * @param date
     * @return
     */
    public static Date getThisMonthEndDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        cale.set(Calendar.MILLISECOND, 999);
        cale.add(Calendar.DAY_OF_MONTH, -1);
        return cale.getTime();
    }

    public static Date getDateBegin(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        cale.set(Calendar.MILLISECOND, 0);
        return cale.getTime();
    }

    public static Date getDateEnd(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        cale.set(Calendar.MILLISECOND, 999);
        return cale.getTime();
    }

    public static Date getUtcDateBegin(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.DATE, -1);
        cale.set(Calendar.HOUR_OF_DAY, 16);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        cale.set(Calendar.MILLISECOND, 0);
        return cale.getTime();
    }

    public static Date getUtcDateEnd(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.HOUR_OF_DAY, 15);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        cale.set(Calendar.MILLISECOND, 999);
        return cale.getTime();
    }
}
