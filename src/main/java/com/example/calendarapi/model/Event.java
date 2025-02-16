package com.example.calendarapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description; // 설명
    private LocalDate startTime; // 시작 날짜
    private LocalDate endTime;   // 종료 날짜
    private String title;        // 제목
    private Boolean completed;   // 완료 여부 
    private LocalDate date;      // 날짜

    // 기본 생성자
    public Event() {
    }

    // 매개변수 있는 생성자
    public Event(String description, LocalDate startTime, LocalDate endTime, String title, Boolean completed, LocalDate date) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.completed = completed;
        this.date = date;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() { 
        return completed;
    }

    public void setCompleted(Boolean completed) { 
        this.completed = completed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

@Override
public String toString() {
    return "Event{" +
            "id=" + id +
            ", title='" + new String(title.getBytes(), StandardCharsets.UTF_8) + '\'' +
            ", description='" + (description != null ? new String(description.getBytes(), StandardCharsets.UTF_8) : "null") + '\'' +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", completed=" + completed +
            ", date=" + date +
            '}';
}

}
