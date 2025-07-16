package ufrn.imd.ia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IaServiceApplication.class, args);
    }

}