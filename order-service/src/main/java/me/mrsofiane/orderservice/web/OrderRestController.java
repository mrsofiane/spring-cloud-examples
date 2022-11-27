package me.mrsofiane.orderservice.web;

import me.mrsofiane.orderservice.entities.Order;
import me.mrsofiane.orderservice.models.Customer;
import me.mrsofiane.orderservice.models.Product;
import me.mrsofiane.orderservice.repositories.OrderRepository;
import me.mrsofiane.orderservice.repositories.ProductItemRepository;
import me.mrsofiane.orderservice.services.CustomerRestClientService;
import me.mrsofiane.orderservice.services.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository,
                               ProductItemRepository productItemRepository,
                               CustomerRestClientService customerRestClientService,
                               InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping("fullOrder/{id}")
    public Order getOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id).get();
        Customer customer = customerRestClientService.getCustomerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(productItem -> {
            Product product = inventoryRestClientService.getProductById(productItem.getProductId());
            productItem.setProduct(product);
        });

        return order;
    }
}
