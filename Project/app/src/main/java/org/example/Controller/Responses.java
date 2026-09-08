package org.example.Controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Record di risposta REST: rappresentazioni JSON piatte (solo id per le associazioni, mai grafi di entità
 * annidati) per evitare cicli di serializzazione Jackson sulle entità JPA bidirezionali di Model/.
 */
final class Responses {
    private Responses() {
    }

    record UtenteResponse(long id, String username, String email, String nome, String cognome, Long teamId) {
    }

    record StaffResponse(long id, String username, String email, String nome, String cognome) {
    }

    record HackathonResponse(long id, String nome, String regolamento, String luogo, BigDecimal premio,
                              int maxTeamPartecipanti, boolean premioErogato, Date scadenzaIscrizioni,
                              Date dataInizio, Date dataFine, String stato, Long teamVincitoreId) {
    }

    record TeamResponse(long id, String nome, String iban, int numMembri, List<Long> membriIds) {
    }

    record IscrizioneResponse(long id, Date dataIscrizione, long teamId, long hackathonId, Long sottomissioneId) {
    }

    record SottomissioneResponse(long id, String titolo, String descrizione, String linkRepository, Date dataInvio,
                                  long iscrizioneId, Long valutazioneId) {
    }

    record ValutazioneResponse(long id, String giudizio, int punteggio, Date dataValutazione, long sottomissioneId) {
    }

    record InvitoResponse(long id, long teamId, long utenteId, String stato) {
    }

    record SegnalazioneResponse(long id, String motivazione, Date dataSegnalazione, String esito, long teamId,
                                 long hackathonId, long mentoreId) {
    }

    record RichiestaDiSupportoResponse(long id, String oggetto, String descrizione, Date dataInvio, String esito,
                                        long iscrizioneId, Long callId) {
    }

    record CallResponse(long id, Date dataCall, int durataMinuti, String linkCall, long richiestaDiSupportoId) {
    }

    record RuoloStaffResponse(long id, long staffId, long hackathonId, String ruolo) {
    }
}
