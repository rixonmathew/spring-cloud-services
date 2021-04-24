package com.rixon.cloud.departmentservice;

import com.rixon.cloud.commons.Department;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collection;
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


    public List<Department> allDepartments() {
        return List.copyOf(allDepartments.values());
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

    public Department byCode(String code) {
        return allDepartments.get(code);
    }


}
