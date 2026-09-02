package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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

    @OneToMany(mappedBy = "team")
    private List<Utente> membri = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invito> invitiInviati = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Iscrizione> iscrizioni = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Segnalazione> segnalazioni = new ArrayList<>();

    @OneToMany(mappedBy = "teamVincitore")
    private List<Hackathon> hackathon = new ArrayList<>();

    public Team(String nome) {
        this.nome = nome;
    }

    public boolean addMembro(Utente membro) {
        return membri.add(membro);
    }
    public boolean removeMembro(Utente membro) {
        return membri.remove(membro);
    }
    public int getNumMembri(){
        return membri.size();
    }
}