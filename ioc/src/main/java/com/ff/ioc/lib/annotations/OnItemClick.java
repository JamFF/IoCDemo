package com.ff.ioc.lib.annotations;

import android.support.annotation.IdRes;

import com.ff.ioc.lib.recyclerview.RView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 该注解作用于在方法
@Retention(RetentionPolicy.RUNTIME) // jvm运行时通过反射获取到该注解的内容
@EventBase(listenerSetter = "setOnItemClickListener",
        listenerType = RView.OnItemClickListener.class, callBackListener = "onItemClick")
public @interface OnItemClick {

    @IdRes int[] value();
}
