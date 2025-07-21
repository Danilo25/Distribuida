package ufrn.imd.conversation.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import ufrn.imd.conversation.dto.AIRequest;
import ufrn.imd.conversation.dto.AIResponse;

public interface Microservice2Client {
    @PostExchange("/ia/conversa")
    AIResponse startConversation(@RequestBody AIRequest request);
}
