package tn.esprit.tpfoyer.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChambreDTO {
    long numeroChambre;
    @Enumerated(EnumType.STRING)
    TypeChambre typeC;
}
