package com.example.Event.Repository;

import com.example.Event.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEventTitle(String title);

    List<Event> findByDate(LocalDate date);

    List<Event> findByCategoryAndLocation(String category, String location);
}
