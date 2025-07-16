package ufrn.imd.mcp.interno.repository;

import ufrn.imd.mcp.interno.model.CalendarEvent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EventRepository {
    
    private final Map<Long, CalendarEvent> events = new ConcurrentHashMap<>();
    private final java.util.concurrent.atomic.AtomicLong counter = new AtomicLong();

    public CalendarEvent save(CalendarEvent event){
        long id = counter.incrementAndGet();
        events.put(id, event);
        return event;
    }

    public Optional<CalendarEvent> findByTitle(String title){
        return events.values().stream()
                .filter(event -> event.title().equalsIgnoreCase(title))
                .findFirst();
    }

    public List<CalendarEvent> findAll(){
        return new ArrayList<>(events.values());
    }

    public boolean deleteByTitle(String title){
        Optional<Long> keyToRemoval = events.entrySet().stream()
                .filter(entry -> entry.getValue().title().equalsIgnoreCase(title))
                .map(Map.Entry::getKey)
                .findFirst();
                
        if(keyToRemoval.isPresent()){
            events.remove(keyToRemoval.get());
            return true;
        }
        return false;
    }
}
