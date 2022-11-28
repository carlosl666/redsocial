package com.caamal.ms.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsUsuariosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUsuariosApiApplication.class, args);
	}

}
