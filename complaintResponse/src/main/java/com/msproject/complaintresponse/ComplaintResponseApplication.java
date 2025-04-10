package com.msproject.complaintresponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class ComplaintResponseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplaintResponseApplication.class, args);
    }

}
