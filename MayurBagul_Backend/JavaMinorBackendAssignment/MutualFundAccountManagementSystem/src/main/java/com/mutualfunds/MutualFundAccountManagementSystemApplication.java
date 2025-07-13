package com.mutualfunds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; 
@SpringBootApplication
@EnableScheduling 
public class MutualFundAccountManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MutualFundAccountManagementSystemApplication.class, args);
    }
}
