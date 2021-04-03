package com.rixon.cloud.commons;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Department {
    private String code;
    private String name;
    private String managerId;
}
