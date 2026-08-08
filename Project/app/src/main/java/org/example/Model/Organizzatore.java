package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "organizzatore")
public class Organizzatore extends Staff {

    public Organizzatore(String username, String nome, String cognome, String email) {
        super(username, nome, cognome, email);
    }
}
