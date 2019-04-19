package com.rixon.cloud.departmentservice;

import com.rixon.cloud.commons.Department;
import com.rixon.cloud.commons.Employee;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DepartmentService {

    @Autowired
    private WebClient.Builder loadBalancedWebClientBuilder;

    @Autowired
    private HttpClientBuilder httpClientBuilder;

    public Flux<Department> allDepartments() {
        return Flux.fromIterable(randomDepartments(10));
    }

    private List<Department> randomDepartments(int count) {
        return IntStream.rangeClosed(1,count).mapToObj(i->{
            Department department = new Department();
            department.setCode("APD"+i);
            department.setManagerId("emp10");
            department.setName("Department of greatness "+i);
            return department;
        }).collect(Collectors.toList());
    }

    public Mono<Department> byCode(String code) {
        Department department = new Department();
        department.setCode(code);
        return Mono.just(department);
    }


    public Flux<Employee> employees(Department department) {
        return loadBalancedWebClientBuilder.baseUrl("http://employee-service")
                .build()
                .get()
                .uri("/employees")
                .retrieve()
                .bodyToFlux(Employee.class);


    }
}
