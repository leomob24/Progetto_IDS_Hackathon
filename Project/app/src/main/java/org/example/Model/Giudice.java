package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("GIUDICE")
public class Giudice extends RuoloStaff {

    public Giudice(Staff staff, Hackathon hackathon) {
        super(staff, hackathon);
    }
}

