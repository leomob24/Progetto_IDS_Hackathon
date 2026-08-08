package org.example.Model.State;
import org.example.Model.Hackathon;
import org.example.Model.Team;


public class StatoInIscrizione implements HackathonState{
    @Override
    public void iscriviTeam(Hackathon hackathon, Team team) {
        if (hackathon.getTeamIscritti().size() >= hackathon.getMaxTeamPartecipanti()) {
            throw new IllegalStateException("Numero massimo di team raggiunto!");
        }
        if (hackathon.getTeamIscritti().contains(team)) {
            throw new IllegalStateException("Il team è già iscritto a questo hackathon!");
        }
        hackathon.getTeamIscritti().add(team);
    }

    @Override
    public String getNome() { return "IN_ISCRIZIONE"; }

}
