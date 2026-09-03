package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import org.example.Model.State.*;
import org.example.dto.DatiHackathon;
import org.example.dto.DatiValutazione;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "hackathon")
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String regolamento;

    @Column(nullable = false)
    private String luogo;

    @Column(nullable = false)
    private BigDecimal premio;

    @Column(nullable = false)
    private int maxTeamPartecipanti;

    @Column(nullable = false)
    private boolean premioErogato;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date scadenzaIscrizioni;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataInizio;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataFine;

    @ManyToOne
    @JoinColumn(name = "team_vincitore_id")
    private Team teamVincitore;

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Iscrizione> teamIscritti = new ArrayList<>();

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Segnalazione> segnalazioni = new ArrayList<>();

    @Convert(converter = HackathonStateConverter.class)
    @Column(name = "stato", nullable = false)
    private HackathonState stato;

    public void iscriviTeam(Team team) {
        stato.iscriviTeam(this, team);
    }

    public void avviaHackathon() {
        stato.avviaHackathon(this);
    }

    public void valutaHackathon() {
        stato.valutaHackathon(this);
    }

    public void concludiHackathon() {
        stato.concludiHackathon(this);
    }

    public void proclamaVincitore(Team team) {
        stato.proclamaVincitore(this, team);
    }

    public void giudicaSottomissione(Sottomissione sottomissione, DatiValutazione datiValutazione) {
        stato.giudicaSottomissione(this, sottomissione, datiValutazione);
    }

    public void aggiungiSegnalazione(Segnalazione segnalazione) {
        stato.aggiungiSegnalazione(this, segnalazione);
    }

    public void squalificaTeam(Segnalazione segnalazione) {
        stato.squalificaTeam(this, segnalazione);
    }

    public Hackathon(DatiHackathon datiHackathon) {
        this.nome = datiHackathon.getNome();
        this.regolamento = datiHackathon.getRegolamento();
        this.luogo = datiHackathon.getLuogo();
        this.premio = datiHackathon.getPremio();
        this.maxTeamPartecipanti = datiHackathon.getMaxTeamPartecipanti();
        this.scadenzaIscrizioni = datiHackathon.getScadenzaIscrizioni();
        this.dataInizio = datiHackathon.getDataInizio();
        this.dataFine = datiHackathon.getDataFine();
        this.stato = new StatoInIscrizione();
    }
}
