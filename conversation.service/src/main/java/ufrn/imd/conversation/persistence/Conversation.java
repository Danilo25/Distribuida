package ufrn.imd.conversation.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conversationId;
    private LocalDateTime createdAt;

    public Conversation(String conversationId) {
        this.conversationId = conversationId;
        this.createdAt = LocalDateTime.now();
    }
}
