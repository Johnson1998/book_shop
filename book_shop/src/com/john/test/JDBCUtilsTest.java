package com.john.test;

import com.john.utils.JDBCUtils;
import org.junit.Test;

/**
 * @author John
 * @create 2021-09-1915:51
 */
public class JDBCUtilsTest {
    @Test
    public void testJDBCUtils(){
        System.out.println(JDBCUtils.getConnection());
    }
}
