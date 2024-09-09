package com.example.k0cp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class CustomGenericFilter extends GenericFilterBean {
    Logger logger = LoggerFactory.getLogger(CustomGenericFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("CustomGenericFilter doFilter");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
