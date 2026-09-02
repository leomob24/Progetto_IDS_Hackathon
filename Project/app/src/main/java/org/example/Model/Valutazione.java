package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "valutazione")
public class Valutazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String giudizio;

    @Column(nullable = false)
    private int punteggio;

    @Column(nullable = false)
    private Date dataValutazione;

    @OneToOne
    @JoinColumn(name = "sottomissione_id", nullable = false, unique = true)
    private Sottomissione sottomissione;
}
