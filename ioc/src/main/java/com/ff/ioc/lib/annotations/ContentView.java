package com.ff.ioc.lib.annotations;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 * author: FF
 * time: 2019-07-15 15:42
 */
@Target(ElementType.TYPE)// 作用于类，接口，枚举
@Retention(RetentionPolicy.RUNTIME)// 需要通过反射获取注解的值
public @interface ContentView {
    // int类型的布局
    @LayoutRes int value();
}
