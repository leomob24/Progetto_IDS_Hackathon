package org.example.Model.State;
import org.example.Model.*;
import org.example.dto.DatiValutazione;


public class StatoInIscrizione implements HackathonState{
    @Override
    public void iscriviTeam(Hackathon hackathon, Team team) {
        if (hackathon.getTeamIscritti().size() >= hackathon.getMaxTeamPartecipanti()) {
            throw new IllegalStateException("Numero massimo di team raggiunto!");
        }
        boolean giaIscritto = hackathon.getTeamIscritti().stream()
                .anyMatch(iscrizione -> iscrizione.getTeam().equals(team));
        if (giaIscritto) {
            throw new IllegalStateException("Il team è già iscritto a questo hackathon!");
        }
        hackathon.getTeamIscritti().add(new Iscrizione(team, hackathon));
    }

    @Override
    public void avviaHackathon(Hackathon hackathon) {
        hackathon.setStato(new StatoInCorso());
    }

    @Override
    public void valutaHackathon(Hackathon hackathon, Team team) {
        throw new IllegalStateException("L'hackathon non è ancora iniziato!");

    }
    @Override
    public void concludiHackathon(Hackathon hackathon) {
        throw new IllegalStateException("L'hackathon non è ancora iniziato!");
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        throw new IllegalStateException("L'hackathon non è ancora iniziato!");
    }

    @Override
    public void giudicaSottomissione(Hackathon hackathon, Sottomissione sottomissione, DatiValutazione datiValutazione) {
        throw new IllegalStateException("L'hackathon non è ancora iniziato!");
    }

    @Override
    public void aggiungiSegnalazione(Hackathon hackathon, Segnalazione segnalazione) {
        throw new IllegalStateException("L'hackathon non è ancora iniziato!");
    }

    @Override
    public void squalificaTeam(Hackathon hackathon, Segnalazione segnalazione) {
        throw new IllegalStateException("L'hackathon non è ancora iniziato!");
    }

    @Override
    public String getNome() { return "IN_ISCRIZIONE"; }

}
