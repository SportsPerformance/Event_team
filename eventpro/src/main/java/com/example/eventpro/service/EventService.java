package com.example.eventpro.service;

import com.example.eventpro.model.Eventpro;
import com.example.eventpro.repo.Eventrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private Eventrepo eventrepo;

    // Create a new event
    public Eventpro createEvent(Eventpro event) {
        return eventrepo.save(event);
    }

    // Retrieve an event by ID
    public Optional<Eventpro> getEventById(Long eventId) {
        return eventrepo.findById(eventId);
    }

    // Retrieve all events
    public List<Eventpro> getAllEvents() {
        return eventrepo.findAll();
    }

    // Update an existing event
    public Eventpro updateEvent(Long eventId, Eventpro updatedEvent) {
        return eventrepo.findById(eventId).map(existingEvent -> {
            // Update the fields of the existing event with the new values
            existingEvent.setEventTitle(updatedEvent.getEventTitle());
            existingEvent.setMeet(updatedEvent.getMeet());
            existingEvent.setVenue(updatedEvent.getVenue());
            existingEvent.setCategory(updatedEvent.getCategory());
            existingEvent.setEventDescription(updatedEvent.getEventDescription());

            // Save the updated event
            return eventrepo.save(existingEvent);
        }).orElse(null); // Return null if the event is not found
    }



    // Delete an event by ID
    public boolean deleteEvent(Long eventId) {
        if (eventrepo.existsById(eventId)) {
            eventrepo.deleteById(eventId);
            return true;
        }
        return false;
    }


}
