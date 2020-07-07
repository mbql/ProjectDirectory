package com.ht;

import com.ht.util.FastDFSTokenUtil;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * @author mbql
 * @date 2020/3/7 19:14
 */
public class SecurityToken {

    @Test
    public void getToken() throws Exception {
        // int ts = (int) Instant.now().getEpochSecond();      //时间戳有问题
        // int ts = (int) System.currentTimeMillis();
        // int ts = (int)LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        // String token = ProtoCommon.getToken("M00/00/00/J2ySFF71eCqAFrdjAALpJURt1EU67.jpeg", ts, "FastDFS1234567890");
        // long ts = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000;
        long ts = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        //开始获取token时间
        Instant instant1 = Instant.ofEpochSecond(ts);
        String startTime = LocalDateTime.ofInstant(instant1, ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("开始获取token时间：" + startTime);
        String token = FastDFSTokenUtil.getToken("M00/00/00/J2ySFF71eCqAFrdjAALpJURt1EU67.jpeg", ts, "FastDFS1234567890");
        StringBuilder sb = new StringBuilder();
        sb.append("?token=").append(token);
        sb.append("&ts=").append(ts);
        System.out.println(sb.toString());
        new HashMap<>();
        //token失效时间
        long expireTime = ts + 900;                 //配置15minus后过期
        Instant instant2 = Instant.ofEpochSecond(expireTime);
        Thread.sleep(900);
        String endTime = LocalDateTime.ofInstant(instant2, ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("token已失效，请重新获取：" + endTime);
    }

    public static void main(String[] args) {
        long epochSecond = Instant.now().getEpochSecond();
        long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(epochSecond + "----" + second);
        Instant instant = Instant.ofEpochMilli(epochSecond);
        String dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long timeMillis = System.currentTimeMillis() / 1000;
        Instant instant1 = Instant.ofEpochMilli(timeMillis);
        String dateTime1 = LocalDateTime.ofInstant(instant1, ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long milli = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000;
        Instant instant2 = Instant.ofEpochMilli(milli);
        String dateTime3 = LocalDateTime.ofInstant(instant2, ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateTime + "---------" +dateTime1 + "-------------" + dateTime3);
        System.out.println(epochSecond + "------------"+ timeMillis + "-----------" + milli);
        Instant instant3 = Instant.ofEpochSecond(second);
        String localDateTime = LocalDateTime.ofInstant(instant3, ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(localDateTime);

    }

}
