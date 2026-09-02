package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Hackathon;
import org.example.Model.Iscrizione;
import org.example.Model.State.StatoConcluso;
import org.example.Model.State.StatoInIscrizione;
import org.example.Model.Team;
import org.example.Repository.RepositoryHackathon;
import org.example.Repository.RepositoryIscrizioni;
import org.example.dto.DatiHackathon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestoreHackathon {
    private final RepositoryHackathon repositoryHackathon;
    private final RepositoryIscrizioni repositoryIscrizioni;
    @Transactional
    public Hackathon creaHackathon(DatiHackathon datiHackathon) {
        infoValide(datiHackathon);
        Hackathon hackathon = new Hackathon(datiHackathon);
        return repositoryHackathon.save(hackathon);
    }

    public void infoValide(DatiHackathon hackathon){
        if(repositoryHackathon.existsByNome(hackathon.getNome())){
            throw new IllegalArgumentException("Hackathon gia esistente");
        }
        if(hackathon.getScadenzaIscrizioni().after(hackathon.getDataInizio())){
            throw new IllegalArgumentException("Scadenza iscrizioni deve essere prima la data di inizio");
        }
        if(hackathon.getDataInizio().after(hackathon.getDataFine())){
            throw new IllegalArgumentException("Data di inizio deve essere prima la data di fine");
        }
    }
    @Transactional(readOnly = true)
    public List<Hackathon> getListaHackathon(){
        return repositoryHackathon.findAll();
    }
    @Transactional(readOnly = true)
    public List<Hackathon> getHackathonInIscrizione(){
        return repositoryHackathon.findByStato(new StatoInIscrizione());
    }
    @Transactional
    public Team iscriviTeam(Team team, Hackathon hackathon) {
        if(hackathon.getMaxTeamPartecipanti()== hackathon.getTeamIscritti().size()){
            throw new IllegalStateException("Numero massimo di team raggiunto!");
        }
        Iscrizione iscrizione= new Iscrizione(team, hackathon);
        repositoryIscrizioni.save(iscrizione);
        return team;
    }
    @Transactional
    public Team proclamaVincitore(Team team, Hackathon hackathon){
        hackathon.proclamaVincitore(team);
        repositoryHackathon.save(hackathon);
        return team;
    }


}
