package com.rixon.cloud.employeeservice;

import com.rixon.cloud.commons.Employee;
import com.rixon.cloud.commons.EmployeeEvent;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeService {

    private Map<String,Employee> allEmployees;

    @PostConstruct
    public void loadEmployees(){
        allEmployees = randomEmployee(10000)
                .stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
    }

    public Flux<Employee> allEmployees() {
        return Flux.fromIterable(allEmployees.values());
    }

    private List<Employee> randomEmployee(int i) {
        return IntStream.rangeClosed(1,i).mapToObj(j->{
            Employee employee = new Employee();
            employee.setId("emp"+j);
            employee.setName("Hocus Pocus"+j);
            employee.setDateOfJoining(LocalDate.now().minusYears(5));
            employee.setDepartment("IT");
            employee.setDesingation("Associated");
            return employee;
        }).collect(Collectors.toList());
    }

    public Mono<Employee> byId(String id) {
        return Mono.just(allEmployees.get(id));
    }

    public Flux<EmployeeEvent> events(Employee employee) {
        return Flux.empty();
    }
}
