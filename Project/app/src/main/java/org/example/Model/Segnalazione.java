package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "segnalazione")
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String motivazione;

    @Column(nullable = false)
    private Date dataSegnalazione;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EsitoSegnalazione esito = EsitoSegnalazione.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "sottomissione_id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "hackathon_id", nullable = false)
    private Hackathon hackathon;

    @ManyToOne
    @JoinColumn(name = "mentore_id", nullable = false)
    private Mentore mentore;

    public Segnalazione(String motivazione, Date dataSegnalazione){
        this.motivazione = motivazione;
        this.dataSegnalazione = dataSegnalazione;
        this.esito = EsitoSegnalazione.PENDENTE;
    }

}
