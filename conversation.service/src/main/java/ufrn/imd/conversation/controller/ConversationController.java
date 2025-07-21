package ufrn.imd.conversation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufrn.imd.conversation.dto.AIResponse;
import ufrn.imd.conversation.service.ConversationService;

@RestController
@RequestMapping("/api/v1/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;
    public record StartRequest(String prompt) {}

    @PostMapping
    public ResponseEntity<AIResponse> startConversation(@RequestBody StartRequest request) {
        AIResponse response = conversationService.iniciarConversaESalvar(request.prompt());
        return ResponseEntity.ok(response);
    }
}