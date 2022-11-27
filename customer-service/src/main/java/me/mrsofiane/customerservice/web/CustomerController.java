package me.mrsofiane.customerservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class CustomerController {
    @Value("${global.params.p1}")
    private String p1;

    @Value("${global.params.p2}")
    private String p2;

    @Value("${customer.params.x1}")
    private String x1;

    @Value("${customer.params.x2}")
    private String x2;

    @GetMapping("/params")
    public Map<String, String> params() {
        return Map.of("p1",p1,"p2",p2,"x1",x1,"x2",x2);
    }
}
