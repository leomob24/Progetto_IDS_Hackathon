package org.example.Repository;

import org.example.Model.Invito;
import org.example.Model.StatoInvito;
import org.example.Model.Team;
import org.example.Model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryInviti extends JpaRepository<Invito, Long> {
    boolean existsByTeamAndUtente(Team team, Utente utente);
    List<Invito> findByUtente(Utente utente);
    List<Invito> findByUtenteAndStato(Utente utente, StatoInvito stato);
}
