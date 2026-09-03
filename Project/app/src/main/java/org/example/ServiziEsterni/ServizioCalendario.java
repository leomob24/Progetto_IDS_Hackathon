package org.example.ServiziEsterni;

import java.util.Date;
import java.util.List;

public interface ServizioCalendario {
    public String prenotaSlot(List<String> emailPartecipanti, Date dataInizio, int durataMinuti);
}
