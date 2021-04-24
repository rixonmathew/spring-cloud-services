package com.rixon.cloud.departmentservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DepartmentServiceMvcApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DepartmentServiceMvcApplication.class).run(args);
    }

}
