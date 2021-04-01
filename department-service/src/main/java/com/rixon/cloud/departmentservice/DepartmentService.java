package com.rixon.cloud.departmentservice;

import com.rixon.cloud.commons.Department;
import com.rixon.cloud.commons.Employee;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DepartmentService {

    private Map<String,Department> allDepartments;

    @PostConstruct
    public void loadAllDepartments() {
        allDepartments = randomDepartments(1000).stream()
                .collect(Collectors.toMap(Department::getCode, Function.identity()));
    }

    @Autowired
    private WebClient.Builder loadBalancedWebClientBuilder;

    public Flux<Department> allDepartments() {
        return Flux.fromIterable(allDepartments.values());
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
        return Mono.just(allDepartments.get(code));
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
