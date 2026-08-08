package org.example.Model;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "staff")
public abstract class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String email;

    public Staff(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

}
