package com.city.common.nio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {
    /**
     * 对外发布的服务的接口地址
     * @return
     */
    Class<?> value();
    // 暂时没用 可以处理版本
    String version() default "";
}