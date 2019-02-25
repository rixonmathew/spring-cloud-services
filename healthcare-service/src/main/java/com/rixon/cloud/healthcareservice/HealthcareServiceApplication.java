package com.rixon.cloud.healthcareservice;

import com.rixon.cloud.commons.HealthCare;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@EnableDiscoveryClient
public class HealthcareServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HealthcareServiceApplication.class).run(args);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(HealthcareService healthcareService) {
        return route(GET("/healthcareplans"),
                request -> ok().body(healthcareService.allHealthcarePlans(), HealthCare.class))
                .andRoute(GET("/healthcareplans/{code}"),
                        request -> ok().body(healthcareService.byCode(request.pathVariable("code")), HealthCare.class));
    }
}
