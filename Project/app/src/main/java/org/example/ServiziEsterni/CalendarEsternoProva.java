package org.example.ServiziEsterni;


import org.example.dto.CalendarEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CalendarEsternoProva implements CalendarEsterno {

    @Override
    public CalendarEvent createEvent(String attendees, String startISO, String endISO, String titolo) {
        String id = UUID.randomUUID().toString();
        String link = "https://meet.fake-calendar.example/" + id;
        //creo a sistema l'evento call di supporto
        return new CalendarEvent(true, id, link);
    }
}