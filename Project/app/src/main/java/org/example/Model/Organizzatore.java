package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "organizzatore")
public class Organizzatore extends Staff {

    @OneToMany(mappedBy = "organizzatore")
    private List<Hackathon> hackathon = new ArrayList<>();

    public Organizzatore(String username, String nome, String cognome, String email, String password) {
        super(username, nome, cognome, email, password);
    }
}
