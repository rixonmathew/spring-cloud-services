package com.rixon.cloud.commons;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    private String id;
    private String name;
    private String email;
    private String desingation;
    private String department;
    private LocalDate dateOfJoining;
}
