package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Hackathon;
import org.example.Model.Iscrizione;
import org.example.Model.State.StatoInIscrizione;
import org.example.Model.Team;
import org.example.Model.builder.ConcreteHackathonBuilder;
import org.example.Model.builder.HackathonBuilder;
import org.example.Repository.RepositoryHackathon;
import org.example.Repository.RepositoryRuoloStaff;
import org.example.Repository.RepositoryTeam;
import org.example.dto.DatiHackathon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestoreHackathon {
    private final RepositoryHackathon repositoryHackathon;
    private final RepositoryRuoloStaff repositoryRuoloStaff;
    private final RepositoryTeam repositoryTeam;

    @Transactional
    public Hackathon creaHackathon(DatiHackathon datiHackathon) {
        infoValide(datiHackathon);

        HackathonBuilder builder = new ConcreteHackathonBuilder();
        builder.reset();
        builder.setNome(datiHackathon.getNome());
        builder.setRegolamento(datiHackathon.getRegolamento());
        builder.setLuogo(datiHackathon.getLuogo());
        builder.setPremio(datiHackathon.getPremio());
        builder.setMaxTeamPartecipanti(datiHackathon.getMaxTeamPartecipanti());
        builder.setScadenzaIscrizioni(datiHackathon.getScadenzaIscrizioni());
        builder.setDataInizio(datiHackathon.getDataInizio());
        builder.setDataFine(datiHackathon.getDataFine());
        builder.setStato(new StatoInIscrizione());

        return repositoryHackathon.save(builder.build());
    }

    public void infoValide(DatiHackathon hackathon){
        if(repositoryHackathon.existsByNome(hackathon.getNome())){
            throw new IllegalArgumentException("Hackathon gia esistente");
        }
        if(hackathon.getScadenzaIscrizioni().after(hackathon.getDataInizio())){
            throw new IllegalArgumentException("Scadenza iscrizioni deve essere prima della data di inizio");
        }
        if(hackathon.getDataInizio().after(hackathon.getDataFine())){
            throw new IllegalArgumentException("La data di inizio deve essere prima della data di fine");
        }
    }

    @Transactional(readOnly = true)
    public List<Hackathon> getListaHackathon(){
        return repositoryHackathon.findAll();
    }

    @Transactional(readOnly = true)
    public List<Hackathon> getHackathonInIscrizione(){
        return repositoryHackathon.findByStato(new StatoInIscrizione());
    }

    @Transactional
    public Team proclamaVincitore(long hackathonId, long teamId){
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));
        Team team = repositoryTeam.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team non trovato"));

        hackathon.proclamaVincitore(team);
        repositoryHackathon.save(hackathon);
        return team;
    }

    /*
    * getClassifica restituisce la lista dei team iscritti al hackathon,
    * ordinati in base al punteggio ottenuto.
    * Esclude le iscrizioni senza sottomissione e senza valutazione.
    */
    @Transactional(readOnly = true)
    public List<Team> getClassifica(long hackathonId) {
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));

        return hackathon.getTeamIscritti().stream()
                .filter(i -> i.getSottomissione() != null && i.getSottomissione().getValutazione() != null)
                .sorted(Comparator.comparingInt(
                        (Iscrizione i) -> i.getSottomissione().getValutazione().getPunteggio()
                ).reversed())
                .map(Iscrizione::getTeam)
                .collect(Collectors.toList());
    }

    /*
    * ruoliStaffAssegnati restituisce true se un certo hackathon ha almeno un organizzatore, un giudice e un mentore.
    */
    @Transactional(readOnly = true)
    public boolean ruoliStaffAssegnati(long hackathonId) {
        Hackathon hackathon = repositoryHackathon.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon non trovato"));

        boolean haOrganizzatore = repositoryRuoloStaff.recuperaOrganizzatoreHackathon(hackathon).isPresent();
        boolean haGiudice = repositoryRuoloStaff.recuperaGiudiceHackathon(hackathon).isPresent();
        boolean haAlmenoUnMentore = !repositoryRuoloStaff.recuperaMentoriHackathon(hackathon).isEmpty();
        return haOrganizzatore && haGiudice && haAlmenoUnMentore;
    }
}
