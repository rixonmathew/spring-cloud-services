package com.rixon.cloud.zuulserver;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@EnableZuulServer
@SpringCloudApplication
public class ZuulServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulServerApplication.class).run(args);
    }
}
