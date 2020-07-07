package com.ht.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author mbql
 * @date 2020/7/5 12:46
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String value() default "";

}
