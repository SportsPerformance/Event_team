package com.example.eventpro.controller;

import com.example.eventpro.model.Eventpro;
import com.example.eventpro.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    // Show all events
    @GetMapping("/event")
    public ResponseEntity<List<Eventpro>> listEvents() {
        List<Eventpro> events = eventService.getAllEvents();
        return ResponseEntity.ok(events); // Return the list of events
    }

    // Create a new event
    @PostMapping("/event")
    public ResponseEntity<Eventpro> createEvent(@RequestBody Eventpro event) {
        Eventpro createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent); // Return the created event with a 201 status
    }

    // Show an event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Eventpro> getEventById(@PathVariable Long id) {
        Optional<Eventpro> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok) // Return 200 OK with event if found
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 Not Found if not found
    }

    // Update an existing event
    @PutMapping("/edit/{id}")
    public ResponseEntity<Eventpro> updateEvent(@PathVariable Long id, @RequestBody Eventpro updatedEvent) {
        Eventpro event = eventService.updateEvent(id, updatedEvent);
        return event != null ? ResponseEntity.ok(event) // Return updated event with 200 OK
                : ResponseEntity.notFound().build(); // Return 404 Not Found if not found
    }

    // Delete an event
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventService.deleteEvent(id)) {

            return  ResponseEntity.noContent().build(); // Return 204 No Content
        }
        return ResponseEntity.notFound().build(); // Return 404 Not Found if not found
    }
}
