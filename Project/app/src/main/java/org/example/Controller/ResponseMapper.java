package org.example.Controller;

import org.example.Controller.Responses.*;
import org.example.Model.*;

import java.util.List;

/**
 * Mappatura entità JPA (Model/) -> record di risposta REST piatti (Responses).
 * Nessuna logica di business: solo estrazione di campi/id, per tenere i controller privi di logica propria
 * e le entità Model/ intoccate da preoccupazioni di serializzazione JSON.
 */
final class ResponseMapper {
    private ResponseMapper() {
    }

    static UtenteResponse toResponse(Utente u) {
        Long teamId = u.getTeam() != null ? u.getTeam().getId() : null;
        return new UtenteResponse(u.getId(), u.getUsername(), u.getEmail(), u.getNome(), u.getCognome(), teamId);
    }

    static StaffResponse toResponse(Staff s) {
        return new StaffResponse(s.getId(), s.getUsername(), s.getEmail(), s.getNome(), s.getCognome());
    }

    static HackathonResponse toResponse(Hackathon h) {
        Long teamVincitoreId = h.getTeamVincitore() != null ? h.getTeamVincitore().getId() : null;
        return new HackathonResponse(h.getId(), h.getNome(), h.getRegolamento(), h.getLuogo(), h.getPremio(),
                h.getMaxTeamPartecipanti(), h.isPremioErogato(), h.getScadenzaIscrizioni(), h.getDataInizio(),
                h.getDataFine(), h.getStato().getNome(), teamVincitoreId);
    }

    static TeamResponse toResponse(Team t) {
        List<Long> membriIds = t.getMembri().stream().map(Utente::getId).toList();
        return new TeamResponse(t.getId(), t.getNome(), t.getIban(), t.getNumMembri(), membriIds);
    }

    static IscrizioneResponse toResponse(Iscrizione i) {
        Long sottomissioneId = i.getSottomissione() != null ? i.getSottomissione().getId() : null;
        return new IscrizioneResponse(i.getId(), i.getDataIscrizione(), i.getTeam().getId(), i.getHackathon().getId(),
                sottomissioneId);
    }

    static SottomissioneResponse toResponse(Sottomissione s) {
        Long valutazioneId = s.getValutazione() != null ? s.getValutazione().getId() : null;
        return new SottomissioneResponse(s.getId(), s.getTitolo(), s.getDescrizione(), s.getLinkRepository(),
                s.getDataInvio(), s.getIscrizione().getId(), valutazioneId);
    }

    static ValutazioneResponse toResponse(Valutazione v) {
        return new ValutazioneResponse(v.getId(), v.getGiudizio(), v.getPunteggio(), v.getDataValutazione(),
                v.getSottomissione().getId());
    }

    static InvitoResponse toResponse(Invito i) {
        return new InvitoResponse(i.getId(), i.getTeam().getId(), i.getUtente().getId(), i.getStato().name());
    }

    static SegnalazioneResponse toResponse(Segnalazione s) {
        return new SegnalazioneResponse(s.getId(), s.getMotivazione(), s.getDataSegnalazione(), s.getEsito().name(),
                s.getTeam().getId(), s.getHackathon().getId(), s.getMentore().getStaff().getId());
    }

    static RichiestaDiSupportoResponse toResponse(RichiestaDiSupporto r) {
        return new RichiestaDiSupportoResponse(r.getId(), r.getOggetto(), r.getDescrizione(), r.getDataInvio(),
                r.getEsito().name(), r.getIscrizione().getId());
    }

    static RuoloStaffResponse toResponse(RuoloStaff r) {
        String ruolo = switch (r) {
            case Giudice ignored -> "GIUDICE";
            case Organizzatore ignored -> "ORGANIZZATORE";
            case Mentore ignored -> "MENTORE";
            default -> r.getClass().getSimpleName().toUpperCase();
        };
        return new RuoloStaffResponse(r.getId(), r.getStaff().getId(), r.getHackathon().getId(), ruolo);
    }
}
