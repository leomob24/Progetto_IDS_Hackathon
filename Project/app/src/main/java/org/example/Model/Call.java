package org.example.Model;
import lombok.*;
import jakarta.persistence.*;
import org.example.dto.DatiCall;

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

    public Call(DatiCall datiCall){
        this.dataCall = datiCall.getData();
        this.durataMinuti = datiCall.getDurata();
        this.linkCall = datiCall.getLink();
    }
}
