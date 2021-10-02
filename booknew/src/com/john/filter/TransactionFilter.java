package com.john.filter;

import com.john.util.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author John
 * @create 2021-09-3014:37
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtils.commitAndClose(); // �ύ����
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose(); // �ع�����
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
