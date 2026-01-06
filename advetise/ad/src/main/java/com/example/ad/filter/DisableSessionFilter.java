package com.example.ad.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// 拦截所有请求，禁用Session
@WebFilter(urlPatterns = "/*")
public class DisableSessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 包装请求，重写getSession()方法，直接返回null，阻断递归
        HttpServletRequest wrappedRequest = new HttpServletRequestWrapper((HttpServletRequest) request) {
            @Override
            public HttpSession getSession(boolean create) {
                return null; // 禁用Session，直接返回null
            }

            @Override
            public HttpSession getSession() {
                return null; // 禁用Session，直接返回null
            }
        };
        chain.doFilter(wrappedRequest, response);
    }
}