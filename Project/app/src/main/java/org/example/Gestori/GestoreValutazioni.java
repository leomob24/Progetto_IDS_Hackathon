package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Hackathon;
import org.example.Model.Sottomissione;
import org.example.Model.Valutazione;
import org.example.Repository.RepositorySottomissioni;
import org.example.Repository.RepositoryValutazione;
import org.example.dto.DatiValutazione;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GestoreValutazioni {
    private final RepositoryValutazione repositoryValutazione;
    private final RepositorySottomissioni repositorySottomissioni;
    @Transactional
    public Valutazione assegnaValutazione(DatiValutazione datiValutazione, Long sottomissione_id) {
        Sottomissione sottomissione = repositorySottomissioni.findById(sottomissione_id)
                .orElseThrow(() -> new IllegalArgumentException("Sottomissione non trovata"));
        checkValutazione(datiValutazione);
        Hackathon hackathon = sottomissione.getIscrizione().getHackathon();
        hackathon.giudicaSottomissione(sottomissione, datiValutazione);
        return repositoryValutazione.save(sottomissione.getValutazione());
    }
    private void checkValutazione(DatiValutazione datiValutazione) {
        if(datiValutazione.getPunteggio() < 0) {
            throw new IllegalArgumentException("Valutazione non valida");
        }
    }
}
