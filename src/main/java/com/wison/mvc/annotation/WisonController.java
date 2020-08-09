package com.wison.mvc.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})//只能在类上使用
@Retention(RetentionPolicy.RUNTIME)//运行时通过反射获取载体
@Documented
public @interface WisonController {
    String value() default "";
}
