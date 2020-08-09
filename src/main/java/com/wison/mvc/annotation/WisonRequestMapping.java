package com.wison.mvc.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//运行时通过反射获取载体
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface WisonRequestMapping {
    String value() default "";
}
