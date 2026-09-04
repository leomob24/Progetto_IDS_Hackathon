package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Hackathon;
import org.example.Model.Iscrizione;
import org.example.Model.Team;
import org.example.Model.Utente;
import org.example.Repository.RepositoryHackathon;
import org.example.Repository.RepositoryIscrizioni;
import org.example.Repository.RepositoryTeam;
import org.example.Repository.RepositoryUtenti;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestoreTeam {
    private final RepositoryTeam repositoryTeam;
    private final RepositoryIscrizioni repositoryIscrizioni;
    private final RepositoryHackathon repositoryHackathon;
    private final RepositoryUtenti repositoryUtenti;

    @Transactional(readOnly = true)
    public boolean valida(String nome){
        return !repositoryTeam.existsByNome(nome);
    }

    @Transactional
    public Team creaTeam(String nome, long utenteId) {
        if(!valida(nome)){
            throw new IllegalArgumentException("Nome team gia esistente");
        }
        Utente membro = repositoryUtenti.findById(utenteId)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        Team team = new Team(nome);
        membro.setTeam(team);
        team.addMembro(membro);
        return repositoryTeam.save(team);
    }

    @Transactional
    public Team addIban(Team team, String iban){
        team.setIban(iban);
        return repositoryTeam.save(team);
    }

    @Transactional(readOnly = true)
    public boolean esisteIscrizione(long hackathonId, long teamId){
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        Team team = repositoryTeam.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team non trovato"));
        return repositoryIscrizioni.existsByTeamAndHackathon(team, hackathon);
    }

    @Transactional
    public boolean iscriviTeam(long hackathonId, long teamId) {
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        Team team = repositoryTeam.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team non trovato"));

        hackathon.iscriviTeam(team);
        repositoryHackathon.save(hackathon);
        return true;
    }
    @Transactional
    public Utente rimuoviMembro(long utente_id){
        Utente membro = repositoryUtenti.findById(utente_id)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        membro.setTeam(null);
        if(membro.getTeam().getNumMembri()==0){
            repositoryTeam.delete(membro.getTeam());
        }
        return repositoryUtenti.save(membro);
    }
    @Transactional(readOnly = true)
    public List<Team> recuperaTeamIscritti(long hackathonId){
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        return hackathon.getTeamIscritti().stream()
                .map(Iscrizione::getTeam)
                .collect(Collectors.toList());
    }
}
