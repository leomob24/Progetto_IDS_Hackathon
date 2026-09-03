package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Hackathon;
import org.example.Model.Team;
import org.example.Model.Utente;
import org.example.Repository.RepositoryHackathon;
import org.example.Repository.RepositoryIscrizioni;
import org.example.Repository.RepositoryTeam;
import org.example.Repository.RepositoryUtenti;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        team.addMembro(membro);
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
}
