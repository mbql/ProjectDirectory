package com.ht.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author mbql
 * @date 2020/7/5 12:45
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    String value() default  "";

}
