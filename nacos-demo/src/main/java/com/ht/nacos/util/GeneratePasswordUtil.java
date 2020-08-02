package com.ht.nacos.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author mbql
 * @date 2020/8/2 15:31
 */
public class GeneratePasswordUtil {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("nacos"));
    }

}
