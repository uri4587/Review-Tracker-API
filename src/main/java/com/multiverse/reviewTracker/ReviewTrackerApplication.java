package com.multiverse.reviewTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Import(DataSourceInitializerConfig.class)
public class ReviewTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewTrackerApplication.class, args);
	}

}
