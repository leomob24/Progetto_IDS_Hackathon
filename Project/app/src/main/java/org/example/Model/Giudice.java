package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "giudice")
public class Giudice extends Staff {

    public Giudice(String username, String nome, String cognome, String email, String password) {
        super(username, nome, cognome, email, password);
    }
}

