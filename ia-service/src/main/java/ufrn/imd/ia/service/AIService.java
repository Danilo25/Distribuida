package ufrn.imd.ia.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String conversarComIa(String textoDoUsuario, String conversationId) {
        return chatClient.prompt()
                .system(systemSpec -> systemSpec.text(
                        "Você é um assistente de calendário. Sua função é ajudar os usuários a criar e listar eventos. " +
                        "Sempre utilize as ferramentas disponíveis ('criarEventoCalendario', 'listarEventosCalendario') para realizar as tarefas. " +
                        "Seja claro e confirme as ações com o usuário. Não invente informações."
                ))
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
                .user(textoDoUsuario)
                .call()
                .content();
    }
}
