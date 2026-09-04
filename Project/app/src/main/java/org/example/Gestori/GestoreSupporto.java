package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.*;
import org.example.Repository.RepositoryHackathon;
import org.example.Repository.RepositoryIscrizioni;
import org.example.Repository.RepositoryRichiesteDiSupporto;
import org.example.Repository.RepositoryTeam;
import org.example.ServiziEsterni.ServizioCalendario;
import org.example.dto.DatiCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestoreSupporto {
    private final ServizioCalendario servizioCalendario;
    private final RepositoryRichiesteDiSupporto repositoryRichiesteDiSupporto;
    private final RepositoryIscrizioni repositoryIscrizioni;
    private final RepositoryTeam repositoryTeam;
    private final RepositoryHackathon repositoryHackathon;
    @Transactional
    public RichiestaDiSupporto creaRichiestaDiSupporto(String oggetto, String descrizione, long iscrizioneId) {
        Iscrizione iscrizione = repositoryIscrizioni.findById(iscrizioneId)
                .orElseThrow(() -> new IllegalArgumentException("Iscrizione non trovata"));
        RichiestaDiSupporto richiestaDiSupporto = new RichiestaDiSupporto(oggetto, descrizione);
        richiestaDiSupporto.setIscrizione(iscrizione);
        return repositoryRichiesteDiSupporto.save(richiestaDiSupporto);
    }
    @Transactional(readOnly = true)
    public List<Iscrizione> recuperaIscrizioniTeam(Long teamId){
        Team team = repositoryTeam.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team non trovato"));
        return repositoryIscrizioni.findByTeam(team);
    }

    private void validaDati(String oggetto, String descrizione){
        if(oggetto==null || descrizione==null){
            throw new IllegalArgumentException("Dati non validi");
        }
    }

    @Transactional
    public List<RichiestaDiSupporto> recuperaRichiesteDiSupporto(long hackathonId) {
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        return repositoryRichiesteDiSupporto.recuperaRichiesteDiSupporto(hackathon);
    }

    @Transactional
    public RichiestaDiSupporto recuperaDati(long richiestaDiSupportoId) {
        return repositoryRichiesteDiSupporto.findById(richiestaDiSupportoId)
                .orElseThrow(() -> new IllegalArgumentException("Richiesta di supporto non trovata"));
    }
    @Transactional
    public Call pianificaCall(long richiestaId, DatiCall datiCall){
        RichiestaDiSupporto richiestaDiSupporto = repositoryRichiesteDiSupporto.findById(richiestaId)
                .orElseThrow(() -> new IllegalArgumentException("Richiesta di supporto non trovata"));
        Call call = new Call(datiCall);
        call.setRichiestaDiSupporto(richiestaDiSupporto);
        repositoryRichiesteDiSupporto.save(richiestaDiSupporto);
        return call;
    }



}
