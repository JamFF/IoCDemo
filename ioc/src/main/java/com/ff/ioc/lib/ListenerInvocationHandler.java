package com.ff.ioc.lib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * description: 拦截事件的回调方法，而去执行自定义的方法
 * author: FF
 * time: 2019-07-15 18:33
 */
public class ListenerInvocationHandler implements InvocationHandler {

    // 拦截目标，比如拦截MainActivity中的方法
    private Object target;

    // 需要拦截的对象，键值队
    private HashMap<String, Method> methodMap = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target != null) {
            // 获取需要拦截的方法名，例如onClick
            String methodName = method.getName();
            // 执行自定义的方法，也就是使用注解的方法
            method = methodMap.get(methodName);
            if (method != null) {
                method.setAccessible(true);
                return method.invoke(target, args);
            }
        }
        return null;
    }

    /**
     * 添加将要拦截的方法
     *
     * @param methodName 拦截的方法，如onClick
     * @param method     执行的方法，也就是使用注解的方法
     */
    public void addMethod(String methodName, Method method) {
        methodMap.put(methodName, method);
    }
}
