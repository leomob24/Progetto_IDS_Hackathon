package org.example.Model.State;
import org.example.Model.*;
import org.example.dto.DatiValutazione;

public interface HackathonState {
    void iscriviTeam(Hackathon hackathon, Team team);
    void avviaHackathon(Hackathon hackathon);
    void valutaHackathon(Hackathon hackathon, Team team);
    void concludiHackathon(Hackathon hackathon);
    void proclamaVincitore(Hackathon hackathon, Team team);
    void giudicaSottomissione(Hackathon hackathon, Sottomissione sottomissione, DatiValutazione datiValutazione);
    void aggiungiSegnalazione(Hackathon hackathon, Segnalazione segnalazione);
    void squalificaTeam(Hackathon hackathon, Segnalazione segnalazione);

    String getNome();
}
