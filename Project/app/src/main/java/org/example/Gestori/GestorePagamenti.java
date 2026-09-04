package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Hackathon;
import org.example.Model.State.StatoConcluso;
import org.example.Repository.RepositoryHackathon;
import org.example.ServiziEsterni.ServizioPagamento;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GestorePagamenti {
    private final RepositoryHackathon repositoryHackathon;
    private final ServizioPagamento servizioPagamento;

    public String erogaPremio(Long hackathonId){
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));

        if (!(hackathon.getStato() instanceof StatoConcluso)) {
            throw new IllegalStateException("Il premio può essere erogato solo ad un hackathon concluso!");
        }
        if (hackathon.isPremioErogato()) {
            throw new IllegalStateException("Il premio è già stato erogato!");
        }
        if (hackathon.getTeamVincitore() == null) {
            throw new IllegalStateException("Non è stato ancora proclamato un vincitore!");
        }

        String transazioneId = servizioPagamento.effetuaPagamento(hackathon.getTeamVincitore(), hackathon.getPremio());

        hackathon.setPremioErogato(true);
        repositoryHackathon.save(hackathon);
        return transazioneId;
    }
}
