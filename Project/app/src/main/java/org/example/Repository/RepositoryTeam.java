package org.example.Repository;

import org.example.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryTeam extends JpaRepository<Team, Long> {
    Optional<Team> findByNome(String nome);
    boolean existsByNome(String nome);
}