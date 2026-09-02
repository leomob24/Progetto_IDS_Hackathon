package org.example.Gestori;
import org.example.Model.Utente;
import org.example.Model.Team;
import org.example.Model.Invito;
import lombok.*;
import org.example.Repository.RepositoryInviti;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GestoreInviti {

    private final RepositoryInviti repositoryInviti;

    @Transactional
    public Invito invita(Utente utente, Team team) {
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
