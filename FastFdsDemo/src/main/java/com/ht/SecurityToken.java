package com.ht;

import org.csource.fastdfs.ProtoCommon;
import org.junit.Test;

import java.time.Instant;

/**
 * @author mbql
 * @date 2020/3/7 19:14
 */
public class SecurityToken {

    @Test
    public void getToken() throws Exception {
        int ts = (int) Instant.now().getEpochSecond();
        String token = ProtoCommon.getToken("M00/00/00/hq_Op15jhaGAZmptAACEtFgpfnw915.jpg", ts, "FastDFS1234567890");
        StringBuilder sb = new StringBuilder();
        sb.append("?token=").append(token);
        sb.append("&ts=").append(ts);
        System.out.println(sb.toString());
    }
}
