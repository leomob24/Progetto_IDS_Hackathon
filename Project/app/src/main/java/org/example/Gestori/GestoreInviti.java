package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Invito;
import org.example.Model.StatoInvito;
import org.example.Model.Team;
import org.example.Model.Utente;
import org.example.Repository.RepositoryInviti;
import org.example.Repository.RepositoryTeam;
import org.example.Repository.RepositoryUtenti;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public Invito accettaInvito(long invitoId){
        Invito invito = repositoryInviti.findById(invitoId)
                .orElseThrow(() -> new IllegalArgumentException("Invito non trovato"));
        Utente membro = invito.getUtente();
        if (invito.getStato() != StatoInvito.IN_ATTESA) {
            throw new IllegalStateException("Invito già gestito");
        }
        if(membro.getTeam() != null){
            throw new IllegalArgumentException("Utente già appartente ad un team");
        }
        invito.setStato(StatoInvito.ACCETTATO);
        membro.setTeam(invito.getTeam());
        invito.getTeam().addMembro(membro);
        for(Invito i : membro.getInviti()){
            if(i!=invito){
                i.setStato(StatoInvito.RIFIUTATO);
                repositoryInviti.save(i);
            }
        }
        return repositoryInviti.save(invito);
    }

    @Transactional
    public Invito rifiutaInvito(long invitoId){
        Invito invito=repositoryInviti.findById(invitoId)
                .orElseThrow(() -> new IllegalArgumentException("Invito non trovato"));
        Utente membro = invito.getUtente();
        if (invito.getStato() != StatoInvito.IN_ATTESA) {
            throw new IllegalStateException("Invito già gestito");
        }
        invito.setStato(StatoInvito.RIFIUTATO);
        return repositoryInviti.save(invito);
    }
    @Transactional
    public List<Invito> recuperaInvitiPendenti(long utenteId){
        Utente utente= repositoryUtenti.findById(utenteId)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        return repositoryInviti.findByUtenteAndStato(utente, StatoInvito.IN_ATTESA);
    }
}
