package org.example.ServiziEsterni.AdapterCalendar;

import lombok.RequiredArgsConstructor;
import org.example.ServiziEsterni.ServizioCalendario;
import org.example.dto.CalendarEvent;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdapterCalendarEsterno implements ServizioCalendario {
    private final CalendarEsterno calendarEsterno;
    @Override
    public String prenotaSlot(List<String> emailPartecipanti, Date dataInizio, int durataMinuti) {
        String attendees = String.join(",", emailPartecipanti);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String startISO = dataInizio.toInstant().atZone(ZoneId.systemDefault()).format(formatter);
        Date dataFine = new Date(dataInizio.getTime() + durataMinuti * 60_000L);
        String endISO = dataFine.toInstant().atZone(ZoneId.systemDefault()).format(formatter);

         CalendarEvent risposta = calendarEsterno.createEvent(attendees, startISO, endISO, "Call di supporto");

        return convertRisposta(risposta);
    }

    private String convertRisposta(CalendarEvent risposta) {
        if (!risposta.isSuccesso()) {
            throw new IllegalStateException("Impossibile prenotare lo slot sul calendario esterno");
        }
        return risposta.getMeetingLink();
    }
}
