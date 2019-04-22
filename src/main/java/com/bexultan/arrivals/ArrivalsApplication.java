package com.bexultan.arrivals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ArrivalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArrivalsApplication.class, args);
	}

}
