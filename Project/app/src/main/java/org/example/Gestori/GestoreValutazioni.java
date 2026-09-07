package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Giudice;
import org.example.Model.Hackathon;
import org.example.Model.Sottomissione;
import org.example.Model.Valutazione;
import org.example.Repository.RepositoryRuoloStaff;
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
    private final RepositoryRuoloStaff repositoryRuoloStaff;

    @Transactional
    public Valutazione assegnaValutazione(DatiValutazione datiValutazione, Long sottomissione_id, long giudiceId) {
        Sottomissione sottomissione = repositorySottomissioni.findById(sottomissione_id)
                .orElseThrow(() -> new IllegalArgumentException("Sottomissione non trovata"));
        checkValutazione(datiValutazione);
        Hackathon hackathon = sottomissione.getIscrizione().getHackathon();

        Giudice giudice = repositoryRuoloStaff.recuperaGiudiceHackathon(hackathon)
                .orElseThrow(() -> new IllegalStateException("Nessun giudice assegnato a questo hackathon"));
        if (giudice.getStaff().getId() != giudiceId) {
            throw new IllegalArgumentException("Solo il giudice assegnato a questo hackathon può valutarne le sottomissioni");
        }

        hackathon.giudicaSottomissione(sottomissione, datiValutazione);
        return repositoryValutazione.save(sottomissione.getValutazione());
    }
    private void checkValutazione(DatiValutazione datiValutazione) {
        if (!validaPunteggio(datiValutazione.getPunteggio())) {
            throw new IllegalArgumentException("Il punteggio deve essere compreso tra 0 e 10");
        }
        if (datiValutazione.getGiudizio() == null || datiValutazione.getGiudizio().isBlank()) {
            throw new IllegalArgumentException("Il giudizio è obbligatorio");
        }
    }

    private boolean validaPunteggio(int punteggio) {
        return punteggio >= 0 && punteggio <= 10;
    }
}
