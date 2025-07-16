package ufrn.imd.mcp.interno.model;

import java.time.LocalDateTime;

public record CalendarEvent(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
    
}
