package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Hackathon;
import org.example.Model.Iscrizione;
import org.example.Model.Sottomissione;
import org.example.Model.Team;
import org.example.Repository.RepositoryHackathon;
import org.example.Repository.RepositoryIscrizioni;
import org.example.Repository.RepositorySottomissioni;
import org.example.Repository.RepositoryTeam;
import org.example.dto.DatiSottomissione;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestoreSottomissioni {
    private final RepositorySottomissioni repositorySottomissioni;
    private final RepositoryIscrizioni repositoryIscrizioni;
    private final RepositoryTeam repositoryTeam;
    private final RepositoryHackathon repositoryHackathon;
    @Transactional
    public Sottomissione inviaSottomissione(DatiSottomissione datiSottomissione, Long iscrizione_id) {
        Iscrizione iscrizione = repositoryIscrizioni.findById(iscrizione_id)
                .orElseThrow(() -> new IllegalArgumentException("Iscrizione non trovata"));
        if(!validaDati(datiSottomissione)) {
            throw new IllegalArgumentException("Dati non validi");
        }
        Sottomissione sottomissione = new Sottomissione(datiSottomissione);
        if(iscrizione.getSottomissione()!=null){
            throw new IllegalArgumentException("Iscrizione ha già una sottomissione");
        }
        sottomissione.setIscrizione(iscrizione);
        iscrizione.setSottomissione(sottomissione);
        return repositorySottomissioni.save(sottomissione);
    }
    @Transactional
    public List<Iscrizione> recuperaIscrizioni (Long team_id){
        Team team = repositoryTeam.findById(team_id)
                .orElseThrow(() -> new IllegalArgumentException("Team non trovato"));
        return repositoryIscrizioni.findByTeam(team);
    }
    @Transactional
    public List<Sottomissione> recuperaSottomissioni(Long hackathon_id) {
        Hackathon hackathon = repositoryHackathon.findById(hackathon_id)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        return repositorySottomissioni.recuperaSottomissioni(hackathon);
    }
    private boolean validaDati(DatiSottomissione datiSottomissione){
        return datiSottomissione.getTitolo() != null &&
                datiSottomissione.getDescrizione() != null &&
                datiSottomissione.getLinkRepository() != null;
    }
    @Transactional(readOnly = true)
    public Sottomissione getSottomissione(Long team_id, Long hackathon_id){
        Team team = repositoryTeam.findById(team_id)
                .orElseThrow(() -> new IllegalArgumentException("Team non trovato"));
        Hackathon hackathon = repositoryHackathon.findById(hackathon_id)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        Sottomissione sottomissione= repositorySottomissioni.getSottomissione(team, hackathon);
        if(sottomissione==null){
            throw new NullPointerException("Non ha una sottomissione");
        }
        return sottomissione;
    }
    @Transactional
    public Sottomissione aggiornaSottomissione(Long sottomissione_id, DatiSottomissione datiSottomissione){
        Sottomissione sottomissione= repositorySottomissioni.findById(sottomissione_id)
                .orElseThrow(() -> new IllegalArgumentException("Sottomissione non trovata"));
        sottomissione.setTitolo(datiSottomissione.getTitolo());
        sottomissione.setDescrizione(datiSottomissione.getDescrizione());
        sottomissione.setLinkRepository(datiSottomissione.getLinkRepository());
        return repositorySottomissioni.save(sottomissione);
    }
}
