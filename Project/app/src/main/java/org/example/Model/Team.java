package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    // TODO: sostituire con la classe Utente quando sarà creata
    @ManyToMany
    @JoinTable(
            name = "team_membri",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private List<Utente> membri = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "team_hackathon",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "hackathon_id")
    )
    private List<Hackathon> hackathon = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invito> invitiInviati = new ArrayList<>();

    public Team(String nome) {
        this.nome = nome;
    }
}