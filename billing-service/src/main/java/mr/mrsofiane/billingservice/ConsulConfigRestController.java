package mr.mrsofiane.billingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConsulConfigRestController {
    @Autowired
   private MyConsulConfig myConsulConfig;

    @GetMapping("/config")
    public MyConsulConfig myConfig() {
        return myConsulConfig;
    }

}
