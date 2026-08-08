package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mentore")
public class Mentore extends Staff {

    public Mentore(String username, String nome, String cognome, String email) {
        super(username, nome, cognome, email);
    }
}
