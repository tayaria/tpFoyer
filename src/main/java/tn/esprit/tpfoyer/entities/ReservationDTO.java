package tn.esprit.tpfoyer.entities;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDTO {
    Date anneeUniversitaire;
    boolean estValide;
}
