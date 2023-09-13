package com.aiis.project;

import com.aiis.project.serialization.SerializationImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

		SerializationImpl serialization = new SerializationImpl();
		serialization.serialize();
		serialization.deserialize();

	}
}
