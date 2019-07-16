package com.ff.ioc.lib.annotations;

import android.support.annotation.IdRes;
import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 * author: FF
 * time: 2019-07-15 18:05
 */
@Target(ElementType.METHOD)// 作用于方法
@Retention(RetentionPolicy.RUNTIME)// 需要通过反射获取注解的值
@EventBase(listenerSetter = "setOnClickListener", listenerType = View.OnClickListener.class,
        callBackListener = "onClick")
public @interface OnClick {
    // int类型的布局
    @IdRes int[] value();
}
