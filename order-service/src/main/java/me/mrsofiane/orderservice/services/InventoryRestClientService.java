package me.mrsofiane.orderservice.services;

import me.mrsofiane.orderservice.models.Customer;
import me.mrsofiane.orderservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryRestClientService {

    @GetMapping("/products/{id}?projection=fullProduct")
    Product getProductById(@PathVariable Long id);

    @GetMapping("/products?projection=fullProduct")
    PagedModel<Product> getProducts();
}
