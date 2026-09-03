package org.example.Repository;

import org.example.Model.Hackathon;
import org.example.Model.Iscrizione;
import org.example.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryIscrizioni extends JpaRepository<Iscrizione, Long> {
    List<Iscrizione> findByTeam( Team team );
    List<Iscrizione> findByHackathon( Hackathon hackathon );
    Iscrizione findByTeamAndHackathon( Team team, Hackathon hackathon );
    boolean existsByTeamAndHackathon( Team team, Hackathon hackathon );
}
