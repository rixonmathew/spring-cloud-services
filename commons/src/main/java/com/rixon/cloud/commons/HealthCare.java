package com.rixon.cloud.commons;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HealthCare {
    private String type;
    private String code;
    private String provider;
    private BigDecimal coverageAmount;

}
