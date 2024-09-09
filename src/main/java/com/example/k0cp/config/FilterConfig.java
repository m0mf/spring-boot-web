package com.example.k0cp.config;

import com.example.k0cp.filter.CustomGenericFilter;
import com.example.k0cp.filter.CustomOnceRequestFilter;
import com.example.k0cp.service.FilterService;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomGenericFilter> filterRegistrationBean() {
        FilterRegistrationBean<CustomGenericFilter> filterRegistrationBean =
                new FilterRegistrationBean<CustomGenericFilter>(new CustomGenericFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CustomOnceRequestFilter> secondRegistrationBean(FilterService filterService) {
        FilterRegistrationBean<CustomOnceRequestFilter> filterRegistrationBean =
                new FilterRegistrationBean<CustomOnceRequestFilter>(new CustomOnceRequestFilter(filterService));
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);

        return filterRegistrationBean;
    }


}
