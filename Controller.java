package com.example.Event.Controller;

import com.example.Event.Model.Event;
import com.example.Event.Repository.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class Controller {

    private final EventRepository eventRepository;

    // Constructor Injection (Recommended)

    public Controller(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // GET: Retrieve a specific event by ID (using @PathVariable)
    @GetMapping("/GetEvent/{id}")
    public Event getEvent(@PathVariable Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
    }

    // GET: Retrieve all events
    @GetMapping("/GetAllEvent")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    // POST: Save a single event
    @PostMapping("/postEvent")
    public Event postEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    // POST: Save multiple events
    @PostMapping("/postAllEvent")
    public List<Event> postEvents(@RequestBody List<Event> events) {
        return eventRepository.saveAll(events);
    }
}
