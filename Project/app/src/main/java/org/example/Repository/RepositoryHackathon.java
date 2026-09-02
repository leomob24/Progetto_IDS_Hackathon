package org.example.Repository;

import org.example.Model.Hackathon;
import org.example.Model.State.HackathonState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface RepositoryHackathon extends JpaRepository<Hackathon, Long> {
    Optional<Hackathon> findByNome(String nome);
    boolean existsByNome(String nome);
    List<Hackathon> findByStato(HackathonState stato);
}
