package com.sanches.jogodecartas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JogoDeCartasApplication {

	public static void main(String[] args) {
		SpringApplication.run(JogoDeCartasApplication.class, args);
	}

}
