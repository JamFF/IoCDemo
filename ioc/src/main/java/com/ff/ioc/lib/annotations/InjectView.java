package com.ff.ioc.lib.annotations;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 * author: FF
 * time: 2019-07-15 18:01
 */
@Target(ElementType.FIELD)// 作用于属性
@Retention(RetentionPolicy.RUNTIME)// 需要通过反射获取注解的值
public @interface InjectView {
    // int类型的控件
    @IdRes int value();
}
