package com.rixon.cloud.eurekaserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServerApplication.class).run(args);
    }

    @Bean
    public FilterRegistrationBean<CustomRequestDumperFilter> requestDumperFilter() {
        FilterRegistrationBean<CustomRequestDumperFilter> registration = new FilterRegistrationBean<>();
        CustomRequestDumperFilter requestDumperFilter = new CustomRequestDumperFilter();
        registration.setFilter(requestDumperFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
