package org.example.Gestori;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.Model.*;
import org.example.Repository.RepositoryRuoloStaff;
import org.example.Repository.RepositoryStaff;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestoreStaff {
    private final RepositoryStaff repositoryStaff;
    private final RepositoryRuoloStaff repositoryRuoloStaff;
    @Transactional
    public List<Staff> getMentoriNonAssegnati(Hackathon hackathon){
        List<Staff> membriStaff = repositoryStaff.findAll();
        List<Mentore> mentoriAssegnati = repositoryRuoloStaff.recuperaMentoriHackathon(hackathon);

        Set<Long> idGiaAssegnati = mentoriAssegnati.stream()
                .map(m -> m.getStaff().getId())
                .collect(Collectors.toSet());

        return membriStaff.stream()
                .filter(s -> !idGiaAssegnati.contains(s.getId()))
                .collect(Collectors.toList());
    }
    @Transactional
    public List<Hackathon> getHackathonAssegnati(Staff staff){
        return repositoryRuoloStaff.recuperaHackathonAssegnati(staff);
    }
    @Transactional
    public Giudice assegnaGiudice(Staff staff, Hackathon hackathon){
        if (repositoryRuoloStaff.recuperaGiudiceHackathon(hackathon).isPresent()) {
            throw new IllegalStateException("L'hackathon ha già un giudice assegnato");
        }
        giaAssegnatoHackathon(staff, hackathon);
        Giudice giudice = new Giudice(staff, hackathon);
        return repositoryRuoloStaff.save(giudice);
    }
    @Transactional
    public Organizzatore assegnaOrganizzatore(Staff staff, Hackathon hackathon) {
        if (repositoryRuoloStaff.recuperaOrganizzatoreHackathon(hackathon).isPresent()) {
            throw new IllegalStateException("L'hackathon ha già un organizzatore assegnato");
        }
        giaAssegnatoHackathon(staff, hackathon);
        Organizzatore organizzatore = new Organizzatore(staff, hackathon);
        return repositoryRuoloStaff.save(organizzatore);
    }
    @Transactional
    public List<Mentore> assegnaMentore(List<Staff> staffList, Hackathon hackathon){
        List<Mentore> mentoriAssegnati = new ArrayList<>();
        for (Staff staff : staffList) {
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
