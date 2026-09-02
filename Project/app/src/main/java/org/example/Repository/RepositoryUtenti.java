package org.example.Repository;

import org.example.Model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryUtenti extends JpaRepository<Utente, Long> {
    List<Utente> findByTeamIsNull();
    Optional<Utente> findByUsername(String username);
    boolean existsByUsername(String username);
}