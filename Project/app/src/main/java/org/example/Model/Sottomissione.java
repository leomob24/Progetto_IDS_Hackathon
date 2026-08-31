package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sottomissione")
public class Sottomissione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String Titolo;

    @Column(nullable = false)
    private String Descrizione;

    @Column(nullable = false, unique = true)
    private String linkRepository;

    @Column(nullable = false)
    private Date dataInvio;

    @OneToOne
    @JoinColumn(name = "iscrizione_id", nullable = false)
    private Iscrizione iscrizione;

    @OneToOne(mappedBy = "valutazione_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Valutazione valutazione;
}
