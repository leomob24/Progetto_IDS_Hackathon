package org.example.Model.State;

import org.example.Model.Hackathon;
import org.example.Model.Team;

public class StatoInCorso implements HackathonState {

    @Override
    public void iscriviTeam(Hackathon hackathon, Team team) {
        throw new IllegalStateException("Le iscrizioni sono chiuse!");
    }

    @Override
    public String getNome() { return "IN_CORSO"; }
}
