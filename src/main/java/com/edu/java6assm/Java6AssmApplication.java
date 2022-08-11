package com.edu.java6assm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableScheduling
@EnableEncryptableProperties
public class Java6AssmApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java6AssmApplication.class, args);
	}

}
