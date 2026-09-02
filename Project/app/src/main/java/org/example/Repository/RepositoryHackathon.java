package org.example.Repository;

import org.example.Model.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositoryHackathon extends JpaRepository<Hackathon, Long> {
    Optional<Hackathon> findByNome(String nome);
    boolean existsByNome(String nome);
}
