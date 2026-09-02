package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "iscrizione")
public class Iscrizione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date dataIscrizione;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "hackathon_id", nullable = false)
    private Hackathon hackathon;

    @OneToOne(mappedBy = "iscrizione", cascade = CascadeType.ALL, orphanRemoval = true)
    private Sottomissione sottomissione;

    @OneToMany(mappedBy = "iscrizione", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RichiestaDiSupporto> richiesteDiSupportoList;
}
