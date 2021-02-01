package com.demo.atc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testng.TestNG;
import com.demo.operation.TestExecutor;

@SpringBootApplication
public class AtcApplication {
	public static TestNG tng;
	public static void main(String[] args) {
		SpringApplication.run(AtcApplication.class, args);
		tng = new TestNG();
		tng.setTestClasses(new Class[] { TestExecutor.class });
		tng.run();
	}

}
