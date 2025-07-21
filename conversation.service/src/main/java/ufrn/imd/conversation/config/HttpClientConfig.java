package ufrn.imd.conversation.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ufrn.imd.conversation.client.Microservice2Client;

@Configuration
public class HttpClientConfig {

    @Bean
    @LoadBalanced // ESSENCIAL: Habilita o Load Balancing via Eureka
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public Microservice2Client microservice2Client(WebClient.Builder builder) {
        WebClient webClient = builder.baseUrl("http://ia-service").build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        return factory.createClient(Microservice2Client.class);
    }
}
