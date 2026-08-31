package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mentore")
public class Mentore extends Staff {
    @OneToMany(mappedBy = "mentore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Segnalazione> segnalazioni = new ArrayList<>();

    public Mentore(String username, String nome, String cognome, String email, String password) {
        super(username, nome, cognome, email, password);
    }
}
