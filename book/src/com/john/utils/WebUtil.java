package com.john.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author John
 * @create 2021-09-2115:52
 */
public class WebUtil {
    /**
     * 把Map中的值注入到对应的javabean属性中
     * @param value
     * @param bean
     */
    public static Object copyParamToBean(Map value, Object bean){

        try {
            /**
             * 把所有请求参数注入到User对象中
             * **/
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转化成int
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
