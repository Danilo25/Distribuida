package ufrn.imd.ia.controller;

//import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ufrn.imd.ia.service.AIService;
//import ufrn.imd.ia.dto.AIRequest;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ia")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    public record AIRequest(String prompt) {}

    @PostMapping("/conversa")
    public Map<String, String> conversar(
            @RequestBody AIRequest request, 
            @RequestParam(name = "conversationId", required = false) String conversationId
    ) {
        if (conversationId == null || conversationId.isBlank()) {
            conversationId = UUID.randomUUID().toString();
        }
        
        String response = aiService.conversarComIa(request.prompt(), conversationId);
        
        return Map.of(
            "message", response,
            "conversationId", conversationId
        );
    }
}
