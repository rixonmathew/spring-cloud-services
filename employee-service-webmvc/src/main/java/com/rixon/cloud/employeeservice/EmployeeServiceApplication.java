package com.rixon.cloud.employeeservice;

import com.rixon.cloud.commons.Employee;
import com.rixon.cloud.commons.EmployeeEvent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EmployeeServiceApplication.class).run(args);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(EmployeeService employeeService) {
        return route(GET("/employees"),
                request -> ok().body(employeeService.allEmployees(), Employee.class))
                .andRoute(GET("/employees/{id}"),
                        request -> ok().body(employeeService.byId(request.pathVariable("id")),Employee.class))
                .andRoute(GET("/employees/{id}/events"),
                        request ->ok()
                                .contentType(MediaType.APPLICATION_NDJSON)
                                .body(employeeService.byId(request.pathVariable("id"))
                                        .flatMapMany(employeeService::events), EmployeeEvent.class));
    }

}
