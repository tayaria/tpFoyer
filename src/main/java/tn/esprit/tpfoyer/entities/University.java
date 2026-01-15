package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUniversity;
    String nomUniversity;
    String adresseUniversity;
    @OneToOne(cascade = CascadeType.ALL)
    Foyer foyer;

}
