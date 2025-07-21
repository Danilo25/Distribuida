package ufrn.imd.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.calendar.persistence.Event;
import ufrn.imd.calendar.service.EventService;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        return ResponseEntity.ok(service.createEvent(event));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id) {
        return service.findEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        return ResponseEntity.ok(service.findAllEvents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
