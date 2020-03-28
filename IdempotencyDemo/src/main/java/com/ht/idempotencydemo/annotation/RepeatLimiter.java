package com.ht.idempotencydemo.annotation;

import java.lang.annotation.*;

/**
 * @author mbql
 *  幂等性操作的注解
 * @date 2020/3/28 14:53
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatLimiter {
}
