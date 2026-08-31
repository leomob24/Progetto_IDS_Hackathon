package org.example.Model;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "richiesta_di_supporto")
public class RichiestaDiSupporto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String oggetto;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private Date dataInvio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoRichiestaDiSupporto esito = StatoRichiestaDiSupporto.INVIATA;

    @ManyToOne
    @JoinColumn(name = "iscrizione_id", nullable = false)
    private Iscrizione iscrizione;

    @OneToOne(mappedBy = "richiestaDiSupporto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Call call;
}
