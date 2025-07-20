package ufrn.imd.ia.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ufrn.imd.ia.service.AIService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ia")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/conversa")
    public Map<String, String> conversar(@RequestBody String texto, @RequestParam(name = "conversationId", required = false) String conversationId) {
        // Se um ID de conversa não for fornecido, cria um novo
        if (conversationId == null || conversationId.isBlank()) {
            conversationId = UUID.randomUUID().toString();
        }
        
        String response = aiService.conversarComIa(texto, conversationId);
        
        // Retorna um mapa com a resposta e o ID da conversa
        return Map.of(
            "response", response,
            "conversationId", conversationId
        );
    }
}
