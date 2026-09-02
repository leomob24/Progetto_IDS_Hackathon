package org.example.Repository;

import org.example.Model.Sottomissione;
import org.example.Model.Valutazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RepositoryValutazione extends JpaRepository<Valutazione, Long> {

    @Query("from Valutazione v where v.sottomissione = :sottomissione")
    Optional<Valutazione> recuperaValutazione(@Param("sottomissione") Sottomissione sottomissione);
}
