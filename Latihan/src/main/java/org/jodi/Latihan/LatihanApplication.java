package org.jodi.Latihan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.jodi.Latihan.Controller","Config","Impl"})
public class LatihanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LatihanApplication.class, args);
	}

}
