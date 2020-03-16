package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 使用毫秒数来计算目标日期
 * 一秒为1000毫秒
 * 所以一天的毫秒数为：1000*60*60*24
 * 所以计算目标日期的算法为
 *      1、目标日期毫秒数 = 当前毫秒数 - 间隔时间对应的毫秒数
 *      2、然后将毫秒数转化为日期即可得到目标日期
 *
 */
public class DateCalculation {

    public static int calculation(String days){
        //获得时间格式化对象
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        //得到当前的毫秒数
        long now = System.currentTimeMillis();
        //目标时间毫秒数
        long targetTime = 0;
        //时间段天数为：
        long day = Integer.valueOf(days);
        //时间段对应的毫秒数：
        long intervalsDays = day*1000*60*60*24;
        //计算出目标时间的毫秒数：
        targetTime = now - intervalsDays;
        //将目标时间由毫秒数转化为日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(targetTime);
        Date targetDate = calendar.getTime();
        //转化成long类型
        int targetDateInt = Integer.parseInt(sdf.format(targetDate));

        //将结果返回
        return targetDateInt;
    }
}
