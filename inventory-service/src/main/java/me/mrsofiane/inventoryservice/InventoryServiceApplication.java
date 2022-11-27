package me.mrsofiane.inventoryservice;

import me.mrsofiane.inventoryservice.entities.Product;
import me.mrsofiane.inventoryservice.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository) {
		return args -> {
			productRepository.saveAll(List.of(
					Product.builder().name("product 1").price(1200).quantity(20).build(),
					Product.builder().name("product 2").price(800).quantity(10).build(),
					Product.builder().name("product 3").price(1000).quantity(5).build()
			));
			productRepository.findAll().forEach(System.out::println);
		};
	}

}
