package com.caamal.ms.publicaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsPublicacionesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPublicacionesApiApplication.class, args);
	}

}
