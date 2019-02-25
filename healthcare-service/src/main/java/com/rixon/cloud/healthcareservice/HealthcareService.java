package com.rixon.cloud.healthcareservice;

import com.rixon.cloud.commons.Employee;
import com.rixon.cloud.commons.HealthCare;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HealthcareService {

    public Flux<HealthCare> allHealthcarePlans() {
        return randomHealthCarePlans(100);
    }

    public Flux<HealthCare> forEmployee(String employeeId) {
        return randomHealthCarePlans(10);

    }

    private Flux<HealthCare> randomHealthCarePlans(int count) {
        return Flux.fromIterable(IntStream.rangeClosed(1, count)
                .mapToObj(i -> {
                    HealthCare healthCare = new HealthCare();
                    healthCare.setCode("MED"+i);
                    healthCare.setType("MEDICAL");
                    healthCare.setCoverageAmount(new BigDecimal("100000.00"));
                    healthCare.setProvider("AETNA");
                    return healthCare;
                }).collect(Collectors.toList()));
    }

    public Mono<HealthCare> byCode(String code) {
        HealthCare healthCare = new HealthCare();
        healthCare.setCode(code);
        healthCare.setType("DENTAL");
        healthCare.setCoverageAmount(new BigDecimal("50000"));
        healthCare.setProvider("PRUDENT");
        return Mono.just(healthCare);
    }
}
