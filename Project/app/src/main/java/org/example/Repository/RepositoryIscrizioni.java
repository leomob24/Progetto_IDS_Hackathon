package org.example.Repository;

import org.example.Model.Iscrizione;
import org.example.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryIscrizioni extends JpaRepository<Iscrizione, Long> {
    List<Iscrizione> findByTeam( Team team );
}
