package me.mrsofiane.orderservice;

import me.mrsofiane.orderservice.entities.Order;
import me.mrsofiane.orderservice.entities.ProductItem;
import me.mrsofiane.orderservice.enums.OrderStatus;
import me.mrsofiane.orderservice.models.Customer;
import me.mrsofiane.orderservice.models.Product;
import me.mrsofiane.orderservice.repositories.OrderRepository;
import me.mrsofiane.orderservice.repositories.ProductItemRepository;
import me.mrsofiane.orderservice.services.CustomerRestClientService;
import me.mrsofiane.orderservice.services.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OrderRepository orderRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClientService customerRestClientService,
							InventoryRestClientService inventoryRestClientService) {
		return args -> {
			List<Customer> customers = new ArrayList<>(customerRestClientService.getCustomers().getContent());
			List<Product> products = new ArrayList<>(inventoryRestClientService.getProducts().getContent());
			Long customerId = 1L;
			Customer customer = customerRestClientService.getCustomerById(customerId);
			Random random = new Random();

			for (int i = 1; i <20 ; i++) {
				Order order = Order.builder()
						.customerId(customers.get(random.nextInt(customers.size())).getId())
						.orderStatus(Math.random() > 0.5 ? OrderStatus.PENDING : OrderStatus.CREATED)
						.date(new Date())
						.build();
				Order savedOrder = orderRepository.save(order);
				for (int j = 1; j < products.size() ; j++) {
					if (Math.random() >0.70){
						ProductItem productItem = ProductItem.builder()
								.order(savedOrder)
								.productId(products.get(j).getId())
								.quantity(1 + random.nextInt(10))
								.price(products.get(j).getPrice())
								.discount(Math.random())
								.build();
						productItemRepository.save(productItem);
					}
				}
			}
		};
	}

}
