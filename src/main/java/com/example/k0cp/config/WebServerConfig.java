package com.example.k0cp.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServerConfig {
    /**
     * Tomcat Servlet 에서 Response Mime Type 결정을 위한 설정 이며,
     * 다운 되는 파일 확장자 에 따라 Response Content-Type 을 설정 할 수 있다.
     * mjs 파일에 대한 설정이 없을 경우 html/txt 파일로 다운 로드 된다.
     * @return WebServerFactoryCustomizer
     */
    @Bean
    WebServerFactoryCustomizer<TomcatServletWebServerFactory> customize() {
        return factory -> {
            factory.addContextCustomizers(c -> c.addMimeMapping("mjs", "application/javascript"));
        };
    }
}
