package org.example.Model.State;
import org.example.Model.Hackathon;
import org.example.Model.Team;

public class StatoInValutazione implements HackathonState {

    @Override
    public void iscriviTeam(Hackathon hackathon, Team team) {
        throw new IllegalStateException("L'hackathon è in fase di valutazione!");
    }

    @Override
    public String getNome() { return "IN_VALUTAZIONE"; }
}

