package com.rixon.cloud.employeeservice;

import com.rixon.cloud.commons.Employee;
import com.rixon.cloud.commons.EmployeeEvent;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeService {

    public Flux<Employee> allEmployees() {
        return Flux.fromIterable(randomEmployee(100));
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
        return null;
    }

    public Flux<EmployeeEvent> events(Employee employee) {
        return null;
    }
}
