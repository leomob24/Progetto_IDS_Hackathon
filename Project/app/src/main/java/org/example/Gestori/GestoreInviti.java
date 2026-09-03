package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Invito;
import org.example.Model.Team;
import org.example.Model.Utente;
import org.example.Repository.RepositoryInviti;
import org.example.Repository.RepositoryTeam;
import org.example.Repository.RepositoryUtenti;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GestoreInviti {

    private final RepositoryInviti repositoryInviti;
    private final RepositoryUtenti repositoryUtenti;
    private final RepositoryTeam repositoryTeam;

    @Transactional
    public Invito invita(long utenteId, long teamId) {
        Utente utente = repositoryUtenti.findById(utenteId)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        Team team = repositoryTeam.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team non trovato"));

        if(utente.getTeam() != null){
            throw new IllegalArgumentException("Utente già appartente ad un team");
        }
        if(repositoryInviti.existsByTeamAndUtente(team, utente)){
            throw new IllegalArgumentException("Utente già invitato");
        }
        Invito invito = new Invito(team, utente);
        repositoryInviti.save(invito);
        return invito;
    }
}
