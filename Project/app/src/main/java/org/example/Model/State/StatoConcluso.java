package org.example.Model.State;
import org.example.Model.Hackathon;
import org.example.Model.Team;

public class StatoConcluso implements HackathonState {

    @Override
    public void iscriviTeam(Hackathon hackathon, Team team) {
        throw new IllegalStateException("L'hackathon è concluso!");
    }

    @Override
    public String getNome() { return "CONCLUSO"; }
}
