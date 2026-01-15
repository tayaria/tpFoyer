package tn.esprit.tpfoyer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {
    @Id

    String idReservation;
    String typeRes;
    Date anneeUniversitaire;
    boolean estValide;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Etudiant> etudiants;

}
