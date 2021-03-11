package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    MyService2 service2;

    public String service1(String input) {
        return null;
    }

    public String service2(String in) {
        return service2.service(in);
    }
}
