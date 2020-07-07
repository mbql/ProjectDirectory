package com.ht.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author mbql
 * @date 2020/6/26 12:54
 */
public class FastDFSTokenUtil {

    public static String getToken(String remote_filename, long ts, String secret_key) throws UnsupportedEncodingException, NoSuchAlgorithmException, MyException {
        byte[] bsFilename = remote_filename.getBytes(ClientGlobal.g_charset);
        byte[] bsKey = secret_key.getBytes(ClientGlobal.g_charset);
        byte[] bsTimestamp = (new Long(ts)).toString().getBytes(ClientGlobal.g_charset);
        byte[] buff = new byte[bsFilename.length + bsKey.length + bsTimestamp.length];
        System.arraycopy(bsFilename, 0, buff, 0, bsFilename.length);
        System.arraycopy(bsKey, 0, buff, bsFilename.length, bsKey.length);
        System.arraycopy(bsTimestamp, 0, buff, bsFilename.length + bsKey.length, bsTimestamp.length);
        return md5(buff);
    }

    public static String md5(byte[] source) throws NoSuchAlgorithmException {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(source);
        byte[] tmp = md.digest();
        char[] str = new char[32];
        int k = 0;

        for(int i = 0; i < 16; ++i) {
            str[k++] = hexDigits[tmp[i] >>> 4 & 15];
            str[k++] = hexDigits[tmp[i] & 15];
        }

        return new String(str);
    }


}
