package ufrn.imd.mcp.interno.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import ufrn.imd.mcp.interno.model.CalendarEvent;
import ufrn.imd.mcp.interno.repository.EventRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CalendarTools {

    private final EventRepository eventRepository;

    public CalendarTools(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Tool(
            name = "criarEventoCalendario",
            description = "Cria um novo evento no calendário. Requer título, descrição, data e hora de início e fim."
    )
    public Map<String, Object> criarEvento(
            @ToolParam(description = "Título do evento") String title,
            @ToolParam(description = "Descrição detalhada do evento") String description,
            @ToolParam(description = "Data e hora de início (formato ISO, ex: 2024-07-17T10:00:00)") String startTime,
            @ToolParam(description = "Data e hora de fim (formato ISO, ex: 2024-07-17T11:00:00)") String endTime
    ) {
        try {
            LocalDateTime start = LocalDateTime.parse(startTime);
            LocalDateTime end = LocalDateTime.parse(endTime);
            CalendarEvent newEvent = new CalendarEvent(title, description, start, end);
            eventRepository.save(newEvent);
            return Map.of("status", "Evento criado com sucesso!", "evento", newEvent);
        } catch (Exception e) {
            return Map.of("status", "Falha ao criar evento", "erro", e.getMessage());
        }
    }

    @Tool(
            name = "listarEventosCalendario",
            description = "Lista todos os eventos do calendário."
    )
    public List<CalendarEvent> listarEventos() {
        return eventRepository.findAll();
    }
}
