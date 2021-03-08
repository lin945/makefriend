package com.lin945.makefriend.utils;

/**
 * @author lin945
 * @date 2021/3/7 8:31
 * @description
 */
public class UserUtils {
    public static String getUserId() {
        return String.valueOf(HttpContextUtil.request().getAttribute("userid"));
    }
}
