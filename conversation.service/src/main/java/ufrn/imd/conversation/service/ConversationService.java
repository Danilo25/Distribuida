package ufrn.imd.conversation.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ufrn.imd.conversation.client.Microservice2Client;
import ufrn.imd.conversation.dto.AIRequest;
import ufrn.imd.conversation.dto.AIResponse;
import ufrn.imd.conversation.persistence.Conversation;
import ufrn.imd.conversation.persistence.ConversationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationService {

    private final Microservice2Client microservice2Client;
    private final ConversationRepository conversationRepository;

    @CircuitBreaker(name = "iaServiceCircuitBreaker", fallbackMethod = "fallbackConversarComIa")
    @Retry(name = "iaServiceCircuitBreaker")
    public AIResponse iniciarConversaESalvar(String prompt) {
        log.info("Chamando Microservice2 via HttpInterface...");
        
        AIResponse response = microservice2Client.startConversation(new AIRequest(prompt));

        if (response != null && response.getConversationId() != null) {
            conversationRepository.save(new Conversation(response.getConversationId()));
            log.info("ID da conversa salvo com sucesso: {}", response.getConversationId());
        }
        return response;
    }

    public AIResponse fallbackConversarComIa(String prompt, Throwable t) {
        log.error("Fallback acionado para o prompt '{}'. Causa: {}", prompt, t.getMessage());
        AIResponse fallbackResponse = new AIResponse();
        fallbackResponse.setConversationId("fallback-id-error-123");
        fallbackResponse.setMessage("Serviço indisponível. Tente mais tarde.");
        return fallbackResponse;
    }
}
