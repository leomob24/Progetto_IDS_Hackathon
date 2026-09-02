package org.example.Repository;

import org.example.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositoryRuoloStaff extends JpaRepository<RuoloStaff, Long> {

    @Query("from Organizzatore o where o.hackathon = :hackathon")
    Optional<Organizzatore> recuperaOrganizzatoreHackathon(@Param("hackathon") Hackathon hackathon);

    @Query("from Giudice g where g.hackathon = :hackathon")
    Optional<Giudice> recuperaGiudiceHackathon(@Param("hackathon") Hackathon hackathon);

    @Query("from Mentore m where m.hackathon = :hackathon")
    List<Mentore> recuperaMentoriHackathon(@Param("hackathon") Hackathon hackathon);

    @Query("select distinct r.hackathon from RuoloStaff r where r.staff = :staff")
    List<Hackathon> recuperaHackathonAssegnati(@Param("staff") Staff staff);

}
