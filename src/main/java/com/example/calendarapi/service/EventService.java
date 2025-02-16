package com.example.calendarapi.service;

import com.example.calendarapi.model.Event;
import java.util.List;

public interface EventService {

    // 모든 이벤트 가져오기
    List<Event> getAllEvents(); 

    // 새로운 이벤트 추가
    Event createEvent(Event event); 

    // 이벤트 수정하기
    Event updateEvent(Long id, Event eventDetails); 

    // 이벤트 삭제하기
    boolean deleteEvent(Long id); 
}
