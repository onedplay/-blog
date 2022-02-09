package com.wfz.test;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class Student {
    private String name;
    private Integer age;
    private String address;
}
