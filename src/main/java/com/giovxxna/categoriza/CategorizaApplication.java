package com.giovxxna.categoriza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories()
public class CategorizaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategorizaApplication.class, args);
	}

}
