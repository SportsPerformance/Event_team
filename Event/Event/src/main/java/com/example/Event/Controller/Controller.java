package com.example.Event.Controller;

import com.example.Event.Model.Event;
import com.example.Event.Service.ServiceClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class Controller {

    private final ServiceClass service;

    // Constructor Injection
    public Controller(ServiceClass service) {
        this.service = service;
    }

    // GET: Retrieve a specific event by ID (using @PathVariable)
    @GetMapping("/GetEvent/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Event event = service.getEventId(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/GetSingleEvent")
    public ResponseEntity<Event> getSingleEvent() {
        Event event = service.findSingleEvent();
        return ResponseEntity.ok(event);
    }

    // GET: Retrieve all events
    @GetMapping("/GetAllEvent")
    public ResponseEntity<List<Event>> findAllEvents() {
        List<Event> events = service.getEvents();
        return ResponseEntity.ok(events);
    }

    // POST: Save a single event
    @PostMapping("/postEvent")
    public ResponseEntity<Event> postEvent(@RequestBody Event event) {
        Event savedEvent = service.saveEvent(event);
        return ResponseEntity.ok(savedEvent);
    }

    // POST: Save multiple events
    @PostMapping("/postAllEvent")
    public ResponseEntity<List<Event>> postEvents(@RequestBody List<Event> events) {
        List<Event> savedEvents = service.saveEvents(events);
        return ResponseEntity.ok(savedEvents);
    }

    // PUT: Update an event by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = service.updateEventId(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    // PUT: Update event name by name parameter
    @PutMapping("/updateName/{name}")
    public ResponseEntity<Event> updateNameEvent(@PathVariable String name, @RequestBody Event event) {
        Event updatedEvent = service.updateEventName(name, event);
        return ResponseEntity.ok(updatedEvent);
    }

    // DELETE: Delete a single event by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeEvent(@PathVariable Long id) {
        Event deletedEvent = service.deleteEventId(id);
        if (deletedEvent != null) {
            return ResponseEntity.ok("Event deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete multiple events by list of IDs
    @DeleteMapping("/deleteMultiple")
    public ResponseEntity<String> removeEvents(@RequestBody List<Long> eventIds) {
        service.deleteEventById(eventIds);
        return ResponseEntity.ok("Events deleted successfully.");
    }
}
