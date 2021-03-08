package com.lin945.makefriend.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lin945
 * @date 2020-09-20 14:14
 * @description http帮助类.
 * 注意：该帮助类中的所有方法只能在controller请求的线程中使用，请勿在当前线程外的其它线程中使用。
 */
public class HttpContextUtil {

    /**
     * 获取Servlet上下文
     * @return
     */
    public static ServletRequestAttributes context() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取当前请求下的HttpServletRequest对象
     * @return
     */
    public static HttpServletRequest request() {
        return context().getRequest();
    }

    /**
     * 获取当前请求下的HttpServletResponse对象
     * @return
     */
    public static HttpServletResponse response() {
        return context().getResponse();
    }

    /**
     * 从当前HttpServletRequest域中获取属性
     * 注意：此方法作用的域是request
     * @param name 属性名
     * @return 属性值
     */
    public static Object getAttribute(String name) {
        return request().getAttribute(name);
    }

    /**
     * 为当前HttpServletRequest域设置属性
     * 注意：此方法作用的域是request
     * @param name 属性名
     * @param value 属性值
     */
    public static void setAttribute(String name, Object value) {
        request().setAttribute(name, value);
    }

}
