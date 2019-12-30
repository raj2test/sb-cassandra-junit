package com.sb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories
public class SbDemoJunitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbDemoJunitApplication.class, args);
	}

}
