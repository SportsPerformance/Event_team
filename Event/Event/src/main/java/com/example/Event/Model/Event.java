package com.example.Event.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data  // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor  // Generates a constructor with all fields
@Table(name = "Event_TBL")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment primary key
    private Long eventId;

    private String eventTitle;
    private String meetName;
    private String category;

    private LocalDate date;  // Represents the event date

    private String location;

    public String getName() {
        return this.meetName;
    }

    public void setName(String event) {
        this.meetName=meetName;
    }
}

