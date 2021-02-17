package com.example.Silkstore20;

import com.example.Silkstore20.Domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {

			//Add categories
			categoryRepository.save(new Category("Books"));
			categoryRepository.save(new Category("Gym & Fitness"));
			categoryRepository.save(new Category("Technology"));
			categoryRepository.save(new Category("Banned items"));
			categoryRepository.save(new Category("Miscellaneous"));

			// User: String username, String passwordHash, String role, String email
			User user1 = new User("user", "$2y$12$PwYCv9Nfm4vRShh1h1RDZe1fxlocWz3EHPxWN.uYKN8d5NG6zXIjW", "USER",  "user@user.com");
			User user2 = new User("admin", "$2y$12$F0sFhooRHkDu5tkvgyS/7OHoeykyTbY6TObn5xIc3euDD6mSGbJde", "ADMIN", "admin@admin.com");

			userRepository.save(user1);
			userRepository.save(user2);


			///Book: String title, String seller, Integer price, Integer quantity, Category category
			// Add standard items

			productRepository.save(new Product("The Fascist Manifesto", user1, 20, 2, categoryRepository.findByName("Books").get(0), "https://www.dea.gov/sites/default/files/styles/crop_paragraph_multi_image/public/drug_fact_sheets/sites/getsmartaboutdrugs.com/files/styles/drugfactsimage/public/cocainepowder.jpg"));
			productRepository.save(new Product("20kg Dumbell", user1, 100, 1, categoryRepository.findByName("Gym & Fitness").get(0), "https://5.imimg.com/data5/QT/ME/MY-24863451/dumbell-500x500.jpg"));
			productRepository.save(new Product("Mint Cigarettes", user1, 15, 1, categoryRepository.findByName("Banned items").get(0), "https://www.ctvnews.ca/polopoly_fs/1.4184351.1542716693!/httpImage/image.jpg_gen/derivatives/landscape_1020/image.jpg"));



		};
	}

}