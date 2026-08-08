package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
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
    private double premio;

    @Column(nullable = false)
    private int maxTeamSize;

    @Column(nullable = false)
    private int maxTeamPartecipanti;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date scadenzaIscrizioni;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataInizio;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataFine;

    // TODO: sostituire con la classe Staff quando sarà creata
    @ManyToOne
    @JoinColumn(name = "organizzatore_id", nullable = false)
    private Staff organizzatore;

    @ManyToOne
    @JoinColumn(name = "giudice_id")
    private Staff giudice;

    @ManyToMany
    @JoinTable(
            name = "hackathon_mentori",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "mentore_id")
    )
    private List<Staff> mentori = new ArrayList<>();

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> teamIscritti = new ArrayList<>();

    // TODO: sostituire con la classe Sottomissione quando sarà creata
    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sottomissione> sottomissioni = new ArrayList<>();

    // TODO: sostituire con la classe Segnalazione quando sarà creata
    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Segnalazione> segnalazioni = new ArrayList<>();

    // TODO: implementare HackathonState con lo State Pattern
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HackathonState stato;

    public Hackathon(String nome, String regolamento, String luogo, double premio,
                     int maxTeamSize, int maxTeamPartecipanti,
                     Date scadenzaIscrizioni, Date dataInizio, Date dataFine,
                     Staff organizzatore) {
        this.nome = nome;
        this.regolamento = regolamento;
        this.luogo = luogo;
        this.premio = premio;
        this.maxTeamSize = maxTeamSize;
        this.maxTeamPartecipanti = maxTeamPartecipanti;
        this.scadenzaIscrizioni = scadenzaIscrizioni;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.organizzatore = organizzatore;
        this.stato = HackathonState.CREATO;
    }

}