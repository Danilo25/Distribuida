package ufrn.imd.ia.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @CircuitBreaker(name = "iaServiceCircuitBreaker", fallbackMethod = "fallbackConversarComIa")
    @Retry(name = "iaServiceCircuitBreaker")
    @Bulkhead(name = "iaServiceCircuitBreaker")
    @RateLimiter(name = "iaServiceCircuitBreaker")
    public String conversarComIa(String textoDoUsuario, String conversationId) {
    return chatClient.prompt()
            .system(systemSpec -> systemSpec.text(
                "Você é um assistente de calendário. Sua função é ajudar os usuários a criar e listar eventos. " +
                "Use a ferramenta 'sugerirPlanejamentoParaEvento' para ajudar os usuários a se organizarem para um evento futuro. " +
                "Sempre que um evento for criado, ofereça um plano para ele."
            ))
            .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
            .user(textoDoUsuario)
            .call()
            .content();
}

    public String fallbackConversarComIa(String textoDoUsuario, String conversationId, Throwable throwable) {
        return "O serviço de inteligência artificial está temporariamente indisponível. Por favor, tente novamente mais tarde.";
    }
}
