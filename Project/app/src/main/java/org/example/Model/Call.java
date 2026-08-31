package org.example.Model;
import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "call")
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date dataCall;

    @Column(nullable = false)
    private int durataMinuti;

    @Column(nullable = false)
    private String linkCall;

    @OneToOne
    @JoinColumn(name = "richiesta_di_supporto_id", nullable = false)
    private RichiestaDiSupporto richiestaDiSupporto;
}
