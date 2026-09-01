package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("MENTORE")
public class Mentore extends RuoloStaff {
    @OneToMany(mappedBy = "mentore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Segnalazione> segnalazioni = new ArrayList<>();

    public Mentore(Staff staff, Hackathon hackathon) {
        super(staff, hackathon);
    }
}
