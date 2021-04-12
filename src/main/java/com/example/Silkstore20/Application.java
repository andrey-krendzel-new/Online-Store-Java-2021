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
			/*
			userRepository.deleteAll();
			categoryRepository.deleteAll();
			productRepository.deleteAll();
			//Add categories
			categoryRepository.save(new Category("Books"));
			categoryRepository.save(new Category("Gym & Fitness"));
			categoryRepository.save(new Category("Technology"));
			categoryRepository.save(new Category("Banned items"));
			categoryRepository.save(new Category("Miscellaneous"));

			// User: String username, String passwordHash, String role, String email
			Usero user1 = new Usero("user", "$2y$12$PwYCv9Nfm4vRShh1h1RDZe1fxlocWz3EHPxWN.uYKN8d5NG6zXIjW", "USER",  "user@user.com");
			Usero user2 = new Usero("admin", "$2y$12$F0sFhooRHkDu5tkvgyS/7OHoeykyTbY6TObn5xIc3euDD6mSGbJde", "ADMIN", "admin@admin.com");

			userRepository.save(user1);
			userRepository.save(user2);


			///Book: String title, String seller, Integer price, Integer quantity, Category category
			// Add standard items

			productRepository.save(new Product("The Fascist Manifesto", user1, 20, 2, categoryRepository.findByName("Books").get(0), "https://images-na.ssl-images-amazon.com/images/I/517yDCfGWsL._SX331_BO1,204,203,200_.jpg"));
			productRepository.save(new Product("20kg Dumbell", user1, 100, 1, categoryRepository.findByName("Gym & Fitness").get(0), "https://www.rebelsport.com.au/dw/image/v2/BBRV_PRD/on/demandware.static/-/Sites-srg-internal-master-catalog/default/dw9aeb7e7e/images/581433/Rebel_581433_hi-res.jpg"));
			productRepository.save(new Product("Mint Cigarettes", user1, 15, 1, categoryRepository.findByName("Banned items").get(0), "https://epmgaa.media.clients.ellingtoncms.com/img/photos/2020/03/05/Marlboromenthol_t580.jpg"));
 */


		};
	}

}