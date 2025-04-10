package com.empresa.msusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
 // Habilita el cliente Eureka
public class MsUsuariosApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsUsuariosApplication.class, args);
    }
}

