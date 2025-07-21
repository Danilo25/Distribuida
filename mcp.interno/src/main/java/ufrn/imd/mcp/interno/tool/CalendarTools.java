package ufrn.imd.mcp.interno.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Component
public class CalendarTools {

    public CalendarTools() {}
    @Tool(
            name = "sugerirPlanejamentoParaEvento",
            description = "Com base na data de um evento, sugere um plano de tarefas ou lembretes. A data deve estar no formato AAAA-MM-DD."
    )
    public Map<String, String> sugerirPlanejamento(
            @ToolParam(description = "A data final do evento no formato AAAA-MM-DD") String dataEvento
    ) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate eventDate = LocalDate.parse(dataEvento, formatter);
            LocalDate today = LocalDate.now(); // Pega a data atual do sistema

            long daysUntilEvent = ChronoUnit.DAYS.between(today, eventDate);

            if (daysUntilEvent < 0) {
                return Map.of("status", "Planejamento não aplicável", "sugestao", "Este evento já ocorreu.");
            }

            String sugestao = String.format(
                    "Faltam %d dias para o seu evento! Aqui está um plano simples: " +
                    "1. Daqui a %d dias: Comece a planejar os detalhes. " +
                    "2. Faltando 7 dias: Confirme os preparativos. " +
                    "3. Faltando 1 dia: Lembrete final!",
                    daysUntilEvent,
                    daysUntilEvent / 2
            );

            return Map.of("status", "Planejamento sugerido", "sugestao", sugestao);

        } catch (Exception e) {
            return Map.of("status", "Erro ao gerar planejamento", "erro", "Formato de data inválido. Use AAAA-MM-DD.");
        }
    }
}
