package com.example.k0cp.filter;

import com.example.k0cp.service.FilterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
public class CustomOnceRequestFilter extends OncePerRequestFilter {
    private final FilterService filterService;

    public CustomOnceRequestFilter(FilterService filterService) {
        this.filterService = filterService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("CustomOnceRequestFilter");
        filterService.executeService();

        filterChain.doFilter(request, response);
    }
}
