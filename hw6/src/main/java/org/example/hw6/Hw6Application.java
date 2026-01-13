package org.example.hw6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Hw6Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw6Application.class, args);
    }

}
