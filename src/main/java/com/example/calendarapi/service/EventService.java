package com.example.calendarapi.service;

import com.example.calendarapi.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    
    List<Event> getAllEvents();
    Event createEvent(Event event);
    Optional<Event> updateEvent(Long id, Event eventDetails);
    boolean deleteEvent(Long id);
}
