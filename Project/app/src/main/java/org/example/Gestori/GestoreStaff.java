package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.*;
import org.example.Repository.RepositoryHackathon;
import org.example.Repository.RepositoryRuoloStaff;
import org.example.Repository.RepositoryStaff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestoreStaff {
    private final RepositoryStaff repositoryStaff;
    private final RepositoryRuoloStaff repositoryRuoloStaff;
    private final RepositoryHackathon repositoryHackathon;

    @Transactional(readOnly = true)
    public List<Staff> getMentoriNonAssegnati(long hackathonId){
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));

        List<Staff> membriStaff = repositoryStaff.findAll();
        List<Mentore> mentoriAssegnati = repositoryRuoloStaff.recuperaMentoriHackathon(hackathon);

        Set<Long> idGiaAssegnati = mentoriAssegnati.stream()
                .map(m -> m.getStaff().getId())
                .collect(Collectors.toSet());

        return membriStaff.stream()
                .filter(s -> !idGiaAssegnati.contains(s.getId()))
                .collect(Collectors.toList());
    }

    /*
    * getHackathonAssegnati restituisce la lista degli hackathon a cui un certo membro staff
    * ha un qualsiasi ruolo (Giudice, Organizzatore o Mentore).
    */
    @Transactional(readOnly = true)
    public List<Hackathon> getHackathonAssegnati(long staffId){
        Staff staff = repositoryStaff.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff non trovato"));
        return repositoryRuoloStaff.recuperaHackathonAssegnati(staff);
    }

    @Transactional
    public Giudice assegnaGiudice(long staffId, long hackathonId){
        Staff staff = repositoryStaff.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff non trovato"));
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));

        if (repositoryRuoloStaff.recuperaGiudiceHackathon(hackathon).isPresent()) {
            throw new IllegalStateException("L'hackathon ha già un giudice assegnato");
        }
        giaAssegnatoHackathon(staff, hackathon);
        Giudice giudice = new Giudice(staff, hackathon);
        return repositoryRuoloStaff.save(giudice);
    }

    @Transactional
    public Organizzatore assegnaOrganizzatore(long staffId, long hackathonId) {
        Staff staff = repositoryStaff.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff non trovato"));
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));

        if (repositoryRuoloStaff.recuperaOrganizzatoreHackathon(hackathon).isPresent()) {
            throw new IllegalStateException("L'hackathon ha già un organizzatore assegnato");
        }
        giaAssegnatoHackathon(staff, hackathon);
        Organizzatore organizzatore = new Organizzatore(staff, hackathon);
        return repositoryRuoloStaff.save(organizzatore);
    }

    @Transactional
    public List<Mentore> assegnaMentore(List<Long> staffIds, long hackathonId){
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));

        List<Mentore> mentoriAssegnati = new ArrayList<>();
        for (Long staffId : staffIds) {
            Staff staff = repositoryStaff.findById(staffId)
                    .orElseThrow(() -> new IllegalArgumentException("Staff non trovato: " + staffId));
            giaAssegnatoHackathon(staff, hackathon);
            Mentore mentore = new Mentore(staff, hackathon);
            repositoryRuoloStaff.save(mentore);
            mentoriAssegnati.add(mentore);
        }
        return mentoriAssegnati;
    }

    private void giaAssegnatoHackathon(Staff staff, Hackathon hackathon){
        if(repositoryRuoloStaff.recuperaHackathonAssegnati(staff).contains(hackathon)){
            throw new IllegalArgumentException("Membro Staff ha gia un ruolo nell'hackathon");
        }
    }
}
