package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.*;
import org.example.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestoreSegnalazioni {
    private final RepositorySegnalazioni repositorySegnalazioni;
    private final RepositoryTeam repositoryTeam;
    private final RepositoryHackathon repositoryHackathon;
    private final RepositoryStaff repositoryStaff;
    private final RepositoryRuoloStaff repositoryRuoloStaff;

    @Transactional
    public Segnalazione creaSegnalazione(String motivazione, Long teamId, Long hackathonId, Long mentoreId){
        Team team= repositoryTeam.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team non trovato"));
        Hackathon hackathon= repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        Staff staff = repositoryStaff.findById(mentoreId)
                .orElseThrow(() -> new IllegalArgumentException("Staff non trovato"));
        Mentore mentore = repositoryRuoloStaff.recuperaMentoreHackathon(staff, hackathon)
                .orElseThrow(() -> new IllegalArgumentException("Il membro staff non è mentore di questo hackathon"));

        Segnalazione segnalazione = new Segnalazione(motivazione);
        segnalazione.setTeam(team);
        segnalazione.setMentore(mentore);
        hackathon.aggiungiSegnalazione(segnalazione);

        repositoryHackathon.save(hackathon);
        return segnalazione;
    }
    @Transactional(readOnly = true)
    public List<Segnalazione> recuperaSegnalazioni(Long hackathonId){
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        return repositorySegnalazioni.findByHackathon(hackathon);
    }

    @Transactional
    public Team squalifica(Long segnalazioneId){
        Segnalazione segnalazione = repositorySegnalazioni.findById(segnalazioneId)
                .orElseThrow(() -> new IllegalArgumentException("Segnalazione non trovata"));
        if (segnalazione.getEsito() != EsitoSegnalazione.PENDENTE) {
            throw new IllegalStateException("La segnalazione è già stata gestita!");
        }
        Hackathon hackathon = segnalazione.getHackathon();
        hackathon.squalificaTeam(segnalazione);
        return segnalazione.getTeam();
    }
    @Transactional
    public Segnalazione respingiSegnalazione(Long segnalazioneId){
        Segnalazione segnalazione = repositorySegnalazioni.findById(segnalazioneId)
                .orElseThrow(() -> new IllegalArgumentException("Segnalazione non trovata"));
        if (segnalazione.getEsito() != EsitoSegnalazione.PENDENTE) {
            throw new IllegalStateException("La segnalazione è già stata gestita!");
        }
        Hackathon hackathon = segnalazione.getHackathon();
        hackathon.respingiSegnalazione(segnalazione);
        return segnalazione;
    }


}
