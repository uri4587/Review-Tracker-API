package com.multiverse.reviewTracker;

import com.multiverse.reviewTracker.dto.CheckpointRequestDTO;
import com.multiverse.reviewTracker.dto.CheckpointResponseDTO;
import com.multiverse.reviewTracker.dto.FeedbackRequestDTO;
import com.multiverse.reviewTracker.dto.FeedbackResponseDTO;
import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Feedback;
import com.multiverse.reviewTracker.model.Manager;
import com.multiverse.reviewTracker.service.CheckpointService;
import com.multiverse.reviewTracker.service.EngineerService;
import com.multiverse.reviewTracker.service.FeedbackService;
import com.multiverse.reviewTracker.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@Import(DataSourceInitializerConfig.class)
public class ReviewTrackerApplication {
	private static final Logger log = LoggerFactory.getLogger(ReviewTrackerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReviewTrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(EngineerService engineerService,
								  ManagerService managerService,
								  FeedbackService feedbackService,
								  CheckpointService checkpointService) {
		return (args -> {
			log.info("Running");
			managerService.deleteAll();
			engineerService.deleteAll();

			Engineer eng = engineerService.createEngineer(
					"Uri",
					"Sky",
					"no@no.com",
					"password"
			);
			Engineer eng2 = engineerService.createEngineer(
					"Mike",
					"Brown",
					"no@no.com",
					"password"
			);
			Manager manager = managerService.createManager(
					"John",
					"Smith",
					"jsmit@no.com",
					"password"
			);




			managerService.createManagerEngineerJoin(manager.getId(), eng.getId());
			managerService.createManagerEngineerJoin(manager.getId(), eng2.getId());

//			Feedback feedback1 = feedbackService.createFeedback(
//					"learn java"
//			);

//			CheckpointResponseDTO checkpoint1 = checkpointService.createCheckpoint(
//					"learn how DTOs work in Spring Boot",
//					false,
//					feedback1.getId()
//			);


			log.info(eng.toString());
			log.info(engineerService.getEngineerManagers(eng.getId()));

			log.info(manager.toString());
			log.info(managerService.getManagerEngineers(manager.getId()));


		});
	}

}
