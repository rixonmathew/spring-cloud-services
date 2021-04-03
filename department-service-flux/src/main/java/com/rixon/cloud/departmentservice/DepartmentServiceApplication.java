package com.rixon.cloud.departmentservice;

import com.rixon.cloud.commons.Department;
import com.rixon.cloud.commons.Employee;
import com.rixon.cloud.commons.EmployeeEvent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@EnableDiscoveryClient
public class DepartmentServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DepartmentServiceApplication.class).run(args);
    }


    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
    @Bean
    public RouterFunction<ServerResponse> routerFunction(DepartmentService departmentService) {
        return route(GET("/flux/departments"),
                request -> ok()
                        .body(departmentService.allDepartments(), Department.class))
                .andRoute(GET("/flux/departments/{code}"),
                        request -> ok().body(departmentService.byCode(request.pathVariable("code")),Department.class))
                .andRoute(GET("/flux/departments/{code}/employees"),
                        request ->ok()
                                .contentType(MediaType.APPLICATION_NDJSON)
                                .body(departmentService.byCode(request.pathVariable("code"))
                                        .flatMapMany(departmentService::employees), Employee.class));
    }

}
