package com.rixon.cloud.departmentservice;

import com.rixon.cloud.commons.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class DepartmentRestController {

    private DepartmentService departmentService;

    @GetMapping(value = "/departments",consumes = MediaType.ALL_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Department>> allDepartments() {
        return new ResponseEntity<>(departmentService.allDepartments(),HttpStatus.OK);
    }

    @GetMapping(value = "/departments/{code}", consumes = MediaType.ALL_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Department> singleDepartment(@PathVariable("code") String code) {
        return new ResponseEntity<>(departmentService.byCode(code),HttpStatus.OK);
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

}
