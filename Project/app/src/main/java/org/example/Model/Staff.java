package org.example.Model;
import jakarta.persistence.*;
import lombok.*;
import org.example.dto.DatiRegistrazione;

import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {
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

    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private String password;

    public Staff(DatiRegistrazione datiRegistrazione) {
        this.username = datiRegistrazione.getUsername();
        this.nome = datiRegistrazione.getNome();
        this.cognome = datiRegistrazione.getCognome();
        this.email = datiRegistrazione.getEmail();
        this.password = datiRegistrazione.getPassword();
    }

}
