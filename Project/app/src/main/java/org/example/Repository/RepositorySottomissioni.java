package org.example.Repository;

import org.example.Model.Hackathon;
import org.example.Model.Sottomissione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorySottomissioni extends JpaRepository<Sottomissione, Long> {

    @Query("select s from Sottomissione s where s.iscrizione.hackathon = :hackathon")
    List<Sottomissione> recuperaSottomissioni(@Param("hackathon") Hackathon hackathon);
}
