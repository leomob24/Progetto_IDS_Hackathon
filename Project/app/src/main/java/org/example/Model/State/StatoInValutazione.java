package org.example.Model.State;
import org.example.Model.*;
import org.example.dto.DatiValutazione;

import java.util.Date;

public class StatoInValutazione implements HackathonState {

    @Override
    public void iscriviTeam(Hackathon hackathon, Team team) {
        throw new IllegalStateException("L'hackathon è in fase di valutazione!");
    }

    @Override
    public void avviaHackathon(Hackathon hackathon) {
        throw new IllegalStateException("L'hackathon è già stato avviato!");
    }

    @Override
    public void valutaHackathon(Hackathon hackathon) {
        throw new IllegalStateException("L'hackathon è già in fase di valutazione!");
    }

    @Override
    public void concludiHackathon(Hackathon hackathon) {
        if (new Date().before(hackathon.getDataFine())) {
            throw new IllegalStateException("Non è ancora la data di fine dell'hackathon!");
        }
        if (hackathon.getTeamVincitore() == null) {
            throw new IllegalStateException("Non è stato ancora proclamato un vincitore!");
        }
        hackathon.setStato(new StatoConcluso());
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        boolean teamValido = hackathon.getTeamIscritti().stream()
                .anyMatch(i -> i.getTeam().equals(team));
        if (!teamValido) {
            throw new IllegalArgumentException("Il team non è iscritto a questo hackathon!");
        }
        hackathon.setTeamVincitore(team);
    }

    @Override
    public void giudicaSottomissione(Hackathon hackathon, Sottomissione sottomissione, DatiValutazione datiValutazione) {
        if (!sottomissione.getIscrizione().getHackathon().equals(hackathon)) {
            throw new IllegalArgumentException("La sottomissione non appartiene a questo hackathon!");
        }
        if (sottomissione.getValutazione() != null) {
            throw new IllegalStateException("La sottomissione è già stata valutata!");
        }
        Valutazione valutazione = new Valutazione(datiValutazione);
        valutazione.setSottomissione(sottomissione);
        sottomissione.setValutazione(valutazione);
    }
    @Override
    public void aggiungiSegnalazione(Hackathon hackathon, Segnalazione segnalazione) {
        segnalazione.setHackathon(hackathon);
        hackathon.getSegnalazioni().add(segnalazione);
    }
    @Override
    public void squalificaTeam(Hackathon hackathon, Segnalazione segnalazione) {
        if (!segnalazione.getHackathon().equals(hackathon)) {
            throw new IllegalArgumentException("La segnalazione non appartiene a questo hackathon!");
        }
        segnalazione.setEsito(EsitoSegnalazione.ACCOLTA);
        hackathon.getTeamIscritti().removeIf(i -> i.getTeam().equals(segnalazione.getTeam()));
    }

    @Override
    public String getNome() { return "IN_VALUTAZIONE"; }
}

