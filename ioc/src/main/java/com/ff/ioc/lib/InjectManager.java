package com.ff.ioc.lib;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.ff.ioc.lib.annotations.ContentView;
import com.ff.ioc.lib.annotations.EventBase;
import com.ff.ioc.lib.annotations.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description: 注入管理
 * author: FF
 * time: 2019-07-15 15:37
 */
public class InjectManager {

    public static void inject(Activity activity) {

        injectLayout(activity);

        injectViews(activity);

        injectEvents(activity);
    }

    // 布局注入
    private static void injectLayout(Activity activity) {

        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            @LayoutRes int layoutId = contentView.value();
            // 方式一：
            activity.setContentView(layoutId);
            // 方式二：
            /*try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.invoke(activity, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }*/
        }
    }

    // 控件注入
    private static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        // 获取所有属性，包括私有
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                @IdRes int viewId = injectView.value();
                // 方式一：
                Object view = activity.findViewById(viewId);
                try {
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // 方式二：
                /*try {
                    Method method = clazz.getMethod("findViewById", int.class);
                    Object view = method.invoke(activity, viewId);
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }

    // 事件注入
    public static void injectEvents(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                // 获取注解上的注解类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    if (eventBase != null) {
                        // 获取事件的重要三成员
                        String listenerSetter = eventBase.listenerSetter();
                        Class<?> listenerType = eventBase.listenerType();
                        String callBackListener = eventBase.callBackListener();

                        ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                        handler.addMethod(callBackListener, method);

                        // 通过代理的方式，操作这个对象。并且中间拦截事件方法，执行自定义的方法
                        Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, handler);

                        try {
                            // 通过annotationType获取事件注解上的value值
                            Method valueMethod = annotationType.getDeclaredMethod("value");
                            // 执行value方法获取注解的值
                            @IdRes int[] viewIds = (int[]) valueMethod.invoke(annotation);

                            for (int viewId : viewIds) {
                                View view = activity.findViewById(viewId);
                                // 获取指定的方法
                                Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                                // 执行方法
                                setter.invoke(view, listener);
                            }

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
