package ufrn.imd.mcp.interno.controller;

import ufrn.imd.mcp.interno.model.CalendarEvent;
import ufrn.imd.mcp.interno.repository.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/events")
public class mcpInternoController {
    
    private final EventRepository events;

    public mcpInternoController( EventRepository events){
        this.events = events;
    }

    @PostMapping
    public ResponseEntity<CalendarEvent> createEvent(@RequestBody CalendarEvent event) {
        CalendarEvent newEvent = events.save(event);
        System.out.println("Evento Salvo");
        return ResponseEntity.ok(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<CalendarEvent>> getAllEvents(){
        return ResponseEntity.ok(events.findAll());
    }

    @GetMapping("/{title}")
    public ResponseEntity<CalendarEvent> getEventByTitle(@PathVariable String title){
        return events.findByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/title")
    public ResponseEntity<Void> deleteByTitle(@PathVariable String title){
        boolean delete = events.deleteByTitle(title);
        if(delete){
            System.out.println("Evento Deletado : " + title);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
