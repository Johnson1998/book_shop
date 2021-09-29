package com.john.util;

import javax.servlet.http.Cookie;

/**
 * @author John
 * @create 2021-09-2514:49
 */
public class CookieUtils {
    /**
     * 查找指定名称的cookie对象
     * @param name
     * @param cookies
     * @return
     */
    public static Cookie findCookie(String name, Cookie[] cookies){
        if (name == null || cookies == null || cookies.length == 0){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}
