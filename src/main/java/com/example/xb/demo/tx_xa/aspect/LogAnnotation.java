package com.example.xb.demo.tx_xa.aspect;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// 方法和方法参数
@Target({METHOD, PARAMETER})
@Inherited/* 说明子类可以继承父类中的该注解 */
@Retention(RUNTIME)
public @interface LogAnnotation {
    String value() default "";
}
