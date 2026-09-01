package org.example.Gestori;
import org.example.Model.Utente;
import org.example.Model.Team;
import org.example.Model.Invito;
import lombok.*;
import org.example.Repository.RepositoryInviti;

@RequiredArgsConstructor
public class GestoreInviti {

    private final RepositoryInviti repositoryInviti;

    public Invito invita(Utente utente, Team team) {
        if(utente.getTeam() != null){
            throw new IllegalArgumentException("Utente già appartente ad un team");
        }
        if(repositoryInviti.invitoEsistente(team, utente)){
            throw new IllegalArgumentException("Utente già invitato");
        }
        Invito invito = new Invito(team, utente);
        repositoryInviti.aggiungi(invito);
        return invito;
    }


}
