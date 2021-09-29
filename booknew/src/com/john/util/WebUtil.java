package com.john.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author John
 * @create 2021-09-2610:04
 */
public class WebUtil {
    public WebUtil() {
    }

    public static Object copyParamToBean(Map value, Object bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception var3) {
            var3.printStackTrace();

        }

        return bean;
    }

    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception var3) {
            var3.printStackTrace();
            return defaultValue;
        }
    }
}

