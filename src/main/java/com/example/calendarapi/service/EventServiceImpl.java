package com.example.calendarapi.service;

import com.example.calendarapi.model.Event;
import com.example.calendarapi.repository.EventRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> updateEvent(Long id, Event eventDetails) {
        return eventRepository.findById(id)
                .map(event -> {
                    // 모든 필드가 변경될 수 있도록 수정
                    event.setTitle(eventDetails.getTitle());
                    event.setDescription(eventDetails.getDescription());
                    event.setStartTime(eventDetails.getStartTime());
                    event.setEndTime(eventDetails.getEndTime());
                    event.setCompleted(eventDetails.isCompleted());
                    event.setDate(eventDetails.getDate());
                    return eventRepository.save(event);
                });
    }

    @Override
    public boolean deleteEvent(Long id) {
        return eventRepository.findById(id)
                .map(event -> {
                    eventRepository.delete(event);
                    return true;
                })
                .orElse(false);
    }
}
