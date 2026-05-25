package com.clienteServidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClienteServidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteServidorApplication.class, args);
	}}