package com.ff.ioc.lib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 * author: FF
 * time: 2019-07-15 16:29
 */
@Target(ElementType.ANNOTATION_TYPE)// 作用于其它注解之上
@Retention(RetentionPolicy.RUNTIME)// 需要通过反射获取注解的值
public @interface EventBase {

    // 1.监听的方法名，setOnClickListener
    String listenerSetter();

    // 2.监听的对象，View.OnClickListener
    Class<?> listenerType();

    // 3.回调方法，onClick(View v)
    String callBackListener();
}
