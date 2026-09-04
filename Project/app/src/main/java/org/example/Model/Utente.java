package org.example.Model;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.DatiRegistrazione;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invito> inviti = new ArrayList<>();

    public Utente(DatiRegistrazione datiRegistrazione) {
        this.username = datiRegistrazione.getUsername();
        this.password = datiRegistrazione.getPassword();
        this.email = datiRegistrazione.getEmail();
        this.nome = datiRegistrazione.getNome();
        this.cognome = datiRegistrazione.getCognome();
    }
}

