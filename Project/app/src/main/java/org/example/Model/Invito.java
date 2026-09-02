package org.example.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "invito")
public class Invito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoInvito stato = StatoInvito.IN_ATTESA;

    public Invito(Team team, Utente utente) {
        this.team = team;
        this.utente = utente;
        this.stato = StatoInvito.IN_ATTESA;
    }
}
