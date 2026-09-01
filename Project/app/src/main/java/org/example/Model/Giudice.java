package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "giudice")
public class Giudice extends Staff {

    @OneToMany(mappedBy = "giudice")
    private List<Hackathon> hackathon = new ArrayList<>();

    public Giudice(String username, String nome, String cognome, String email, String password) {
        super(username, nome, cognome, email, password);
    }
}

