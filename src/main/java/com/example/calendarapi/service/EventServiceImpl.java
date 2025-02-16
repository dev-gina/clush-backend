package com.example.calendarapi.service;

import com.example.calendarapi.model.Event;
import com.example.calendarapi.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // 모든 이벤트 가져오기
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();  
    }

    // 새로운 이벤트 추가
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // 이벤트 수정하기
    @Override
    public Event updateEvent(Long id, Event eventDetails) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            Event existing = existingEvent.get();
            existing.setTitle(eventDetails.getTitle());
            existing.setDescription(eventDetails.getDescription());
            existing.setStartTime(eventDetails.getStartTime());
            existing.setEndTime(eventDetails.getEndTime());
            existing.setCompleted(eventDetails.getCompleted());
            existing.setDate(eventDetails.getDate());
            return eventRepository.save(existing);
        }
        return null;  
    }

    // 이벤트 삭제하기
    @Override
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;  
    }
}
