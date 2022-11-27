package me.mrsofiane.customerservice;

import me.mrsofiane.customerservice.entities.Customer;
import me.mrsofiane.customerservice.repo.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CostumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostumerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder().name("customer 1").email("customer1@email.com").build(),
					Customer.builder().name("customer 2").email("customer2@email.com").build()
			));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
