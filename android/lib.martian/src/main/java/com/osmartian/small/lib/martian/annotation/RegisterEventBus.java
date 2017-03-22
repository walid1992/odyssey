package com.osmartian.small.lib.martian.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author   : walid
 * Data     : 2016-09-06  18:53
 * Describe :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisterEventBus {
    // 是否注册
    boolean isRegister() default true;
}
