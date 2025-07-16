package ufrn.imd.ia.controller;

import org.springframework.web.bind.annotation.*;
import ufrn.imd.ia.service.AIService;

import java.util.UUID;

@RestController
@RequestMapping("/ia")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/conversa")
    public String conversar(@RequestBody String texto, @RequestParam(name = "conversationId", required = false) String conversationId) {
        // Se um ID de conversa não for fornecido, cria um novo
        if (conversationId == null || conversationId.isBlank()) {
            conversationId = UUID.randomUUID().toString();
        }
        return aiService.conversarComIa(texto, conversationId);
    }
}