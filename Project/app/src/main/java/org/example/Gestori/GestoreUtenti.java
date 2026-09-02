package org.example.Gestori;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.Model.Utente;
import org.example.Repository.RepositoryUtenti;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestoreUtenti {

    private final RepositoryUtenti repositoryUtenti;

    @Transactional(readOnly = true)
    public List<Utente> getUtentiSenaTeam(){
        return repositoryUtenti.findByTeamIsNull();
    }

}
