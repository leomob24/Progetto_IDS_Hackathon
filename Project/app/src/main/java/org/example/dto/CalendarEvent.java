package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/*DTO dell'evento che genera un calendar esterno*/
public class CalendarEvent {
    private final boolean successo;
    private final String eventId;
    private final String meetingLink;
}
