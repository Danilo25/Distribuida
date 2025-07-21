package ufrn.imd.calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ufrn.imd.calendar.persistence.Event;
import ufrn.imd.calendar.persistence.EventRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository repository;

    public Event createEvent(Event event) {
        return repository.save(event);
    }

    public Optional<Event> findEventById(Long id) {
        return repository.findById(id);
    }

    public List<Event> findAllEvents() {
        return repository.findAll();
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
}
