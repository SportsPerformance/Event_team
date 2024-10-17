package com.example.Event.Service;

import com.example.Event.Model.Event;
import com.example.Event.Repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceClass {

    private final EventRepository eventRepository;

    // Constructor injection
    public ServiceClass(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Retrieve a specific event by ID
    public Event getEventId(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
    }

    // Retrieve all events
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    // Save a single event
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    // Save a list of events
    public List<Event> saveEvents(List<Event> events) {
        return eventRepository.saveAll(events);
    }

    // Retrieve a single event (could be used for the latest event or some criteria)
    public Event findSingleEvent() {
        return eventRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No events found."));
    }

    // Update an event by ID
    public Event updateEventId(Long id, Event newEvent) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));

        existingEvent.setName(newEvent.getName());
        existingEvent.setDate(newEvent.getDate());
        existingEvent.setLocation(newEvent.getLocation());

        return eventRepository.save(existingEvent);
    }

    // Update an event name by its ID
    public Event updateEventName(String name, Event event) {
        event.setName(name);
        return eventRepository.save(event);
    }

    // Delete a single event by ID
    public Event deleteEventId(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
        eventRepository.delete(event);
        return event;
    }

    // Delete multiple events by a list of IDs
    public void deleteEventById(List<Long> eventIds) {
        List<Event> events = eventRepository.findAllById(eventIds);
        if (events.isEmpty()) {
            throw new RuntimeException("No events found with the provided IDs.");
        }
        eventRepository.deleteAll(events);
    }

    public List<Event> getEventsByTitle(String title) {
        return eventRepository.findByEventTitle(title);
    }

    public List<Event> getEventsByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    public List<Event> getEventsByCategoryAndLocation(String category, String location) {
        return eventRepository.findByCategoryAndLocation(category, location);
    }

}
