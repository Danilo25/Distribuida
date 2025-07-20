package ufrn.imd.ApiGateway.controller;

import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class FallbackController {
    
    @RequestMapping("/fallback/ia")
    public Mono<String> iaFallback() {
        return Mono.just("A assistente de IA se encontra indisponível no momento!");
    }

    @RequestMapping("/fallback/mcp")
    public Mono<String> mcpInternoFallback() {
        return Mono.just("O serviço de calendário (MCP) não está respondendo. Tente novamente mais tarde.");
    }
}
