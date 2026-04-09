package com.example.payment_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	public CommandLineRunner listEndpoints(RequestMappingHandlerMapping handlerMapping) {
    return args -> {
        System.out.println("--- LISTE DES ROUTES ACTIVES ---");
        handlerMapping.getHandlerMethods().forEach((key, value) -> 
            System.out.println(key + " -> " + value));
        System.out.println("--------------------------------");
    };
}

}
