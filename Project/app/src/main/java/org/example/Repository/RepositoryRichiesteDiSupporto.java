package org.example.Repository;

import org.example.Model.Hackathon;
import org.example.Model.RichiestaDiSupporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryRichiesteDiSupporto extends JpaRepository<RichiestaDiSupporto, Long> {
    @Query("select r from RichiestaDiSupporto r where r.iscrizione.hackathon = :hackathon")
    List<RichiestaDiSupporto> recuperaRichiesteDiSupporto(@Param("hackathon")Hackathon hackathon);

}
