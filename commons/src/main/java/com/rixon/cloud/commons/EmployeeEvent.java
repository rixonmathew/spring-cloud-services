package com.rixon.cloud.commons;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeEvent {
    private String eventName;
    private String description;
    private LocalDate date;
    private boolean compensationImpacted;
    private boolean locationImpacted;
}
