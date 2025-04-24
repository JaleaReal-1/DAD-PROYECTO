package com.example.papmatricula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableFeignClients(basePackages = "com.example.papmatricula.feing")
public class PapMatriculaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PapMatriculaApplication.class, args);
    }

}
