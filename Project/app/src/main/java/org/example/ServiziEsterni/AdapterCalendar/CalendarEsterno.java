package org.example.ServiziEsterni.AdapterCalendar;
import org.example.dto.CalendarEvent;
public interface CalendarEsterno {
    CalendarEvent createEvent(String attendees, String startISO, String endISO, String titolo);
}
