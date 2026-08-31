package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "iscrizione")
public class Iscrizione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private Date dataIscrizione;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @ManyToOne
    @JoinColumn(name = "hackathon_id", nullable = false)
    private Hackathon hackathon;
    @OneToOne
    @JoinColumn(name = "sottomissione_id")
    private Sottomissione sottomissione;
    @OneToMany
    @JoinColumn(name = "iscrizione_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RichiesteDiSupporto> richiesteDiSupportoList;
}
