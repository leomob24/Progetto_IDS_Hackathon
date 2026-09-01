package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruolo_staff")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ruolo_staff", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
public abstract class RuoloStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    protected RuoloStaff(Staff staff, Hackathon hackathon) {
        this.staff = staff;
        this.hackathon = hackathon;
    }
}
