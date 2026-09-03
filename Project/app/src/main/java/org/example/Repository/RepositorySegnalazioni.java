package org.example.Repository;


import org.example.Model.Hackathon;
import org.example.Model.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface RepositorySegnalazioni extends JpaRepository<Segnalazione, Long> {
    List<Segnalazione> findByHackathon(Hackathon hackathon);
}
