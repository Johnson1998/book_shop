package com.john.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author John
 * @create 2021-09-2622:46
 */
public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filterǰ�ô���2");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Filter���ô���2");
    }

    @Override
    public void destroy() {

    }
}
