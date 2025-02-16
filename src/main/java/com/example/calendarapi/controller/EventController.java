package com.example.calendarapi.controller;

import com.example.calendarapi.model.Event;
import com.example.calendarapi.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:5173") 
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // 모든 이벤트 가져오기
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events); 
    }

    // 새로운 이벤트 추가하기
    @PostMapping(value = "/events", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventService.createEvent(event);
        if (savedEvent != null && savedEvent.getId() != null) {
            return ResponseEntity.status(201).body(savedEvent); 
        }
        return ResponseEntity.status(500).body(null);
    }

    // 이벤트 수정하기
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event updatedEvent = eventService.updateEvent(id, eventDetails);
        if (updatedEvent != null) {
            return ResponseEntity.ok(updatedEvent); 
        }
        return ResponseEntity.status(404).build(); 
    }

    // 이벤트 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        boolean isDeleted = eventService.deleteEvent(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.status(404).build(); 
    }
}
