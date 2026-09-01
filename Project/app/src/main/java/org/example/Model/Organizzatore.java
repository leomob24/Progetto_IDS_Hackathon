package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("ORGANIZZATORE")
public class Organizzatore extends RuoloStaff {

    public Organizzatore(Staff staff, Hackathon hackathon) {
        super(staff, hackathon);
    }

}
