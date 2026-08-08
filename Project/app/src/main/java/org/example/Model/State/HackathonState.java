package org.example.Model.State;
import org.example.Model.*;

public interface HackathonState {
    void iscriviTeam(Hackathon hackathon, Team team);
    /*
    void avvia(Hackathon hackathon);
    void consegnaSottomissione(Hackathon hackathon, Sottomissione sottomissione);
    void aggiungiSegnalazione(Hackathon hackathon, Segnalazione segnalazione);
    void chiudi(Hackathon hackathon);
    void giudicaSottomissione(Hackathon hackathon, Sottomissione sottomissione, int punteggio, String giudizio);
    void proclamaVincitore(Hackathon hackathon, Team team);
     */
    String getNome();
}
