package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
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

    public Utente(String username, String password, String email, String nome, String cognome) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
    }
}

