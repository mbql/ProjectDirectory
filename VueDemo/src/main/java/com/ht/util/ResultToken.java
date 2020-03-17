package com.ht.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author mbql
 * @date 2020/3/15 15:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultToken {
    private String token;
    private ResultMessage resultMessage;

    public ResultToken(ResultMessage resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * 生成Token
     * @return
     */
    public static String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5).replace("==","");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 组装数据
     * @param token
     * @param message
     * @return
     */
    public Map<String,Object> wrapData(String token, ResultMessage message){
        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("message",message);
        return map;
    }
}
