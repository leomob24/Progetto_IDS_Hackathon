package org.example.Model.State;
import org.example.Model.*;
import org.example.dto.DatiValutazione;

public class StatoConcluso implements HackathonState {

    @Override
    public void iscriviTeam(Hackathon hackathon, Team team) {
        throw new IllegalStateException("L'hackathon è concluso!");
    }

    @Override
    public void avviaHackathon(Hackathon hackathon) {
        throw new IllegalStateException("L'hackathon è concluso!");
    }

    @Override
    public void valutaHackathon(Hackathon hackathon, Team team) {
        throw new IllegalStateException("L'hackathon è concluso!");
    }

    @Override
    public void concludiHackathon(Hackathon hackathon) {
        throw new IllegalStateException("L'hackathon è già concluso!");
    }
    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        throw new IllegalStateException("L'hackathon è già concluso!");
    }
    @Override
    public void giudicaSottomissione(Hackathon hackathon, Sottomissione sottomissione, DatiValutazione datiValutazione){
        throw new IllegalStateException("L'hackathon è già concluso!");
    }
    @Override
    public void aggiungiSegnalazione(Hackathon hackathon, Segnalazione segnalazione) {
        throw new IllegalStateException("L'hackathon è già concluso!");
    }
    @Override
    public void squalificaTeam(Hackathon hackathon, Segnalazione segnalazione) {
        throw new IllegalStateException("L'hackathon è già concluso!");
    }

    @Override
    public String getNome() { return "CONCLUSO"; }
}
