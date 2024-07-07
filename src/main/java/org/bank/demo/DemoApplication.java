package org.bank.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		System.out.println("Hello world");
		SystemManager manager = context.getBean(SystemManager.class);
		manager.run();
	}

}
