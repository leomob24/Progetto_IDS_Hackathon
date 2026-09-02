package org.example.Model.State;

import org.example.Model.*;
import org.example.dto.DatiValutazione;

public class StatoInCorso implements HackathonState {

    @Override
    public void iscriviTeam(Hackathon hackathon, Team team) {
        throw new IllegalStateException("Le iscrizioni sono chiuse!");
    }

    @Override
    public void avviaHackathon(Hackathon hackathon) {
        throw new IllegalStateException("L'hackathon è già in corso!");
    }

    @Override
    public void valutaHackathon(Hackathon hackathon, Team team) {
        hackathon.setStato(new StatoInValutazione());
    }

    @Override
    public void concludiHackathon(Hackathon hackathon) {
        throw new IllegalStateException("L'hackathon deve prima essere valutato!");
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        throw new IllegalStateException("L'hackathon è in corso!");
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
    public String getNome() { return "IN_CORSO"; }
}
