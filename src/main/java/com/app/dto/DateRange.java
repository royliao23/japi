package com.app.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateRange {
    @JsonProperty("start")
    private LocalDate start;
    
    @JsonProperty("end")
    private LocalDate end;

    // Constructors
    public DateRange() {}

    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    // Getters and Setters
    public LocalDate getStart() { return start; }
    public void setStart(LocalDate start) { this.start = start; }

    public LocalDate getEnd() { return end; }
    public void setEnd(LocalDate end) { this.end = end; }
}
