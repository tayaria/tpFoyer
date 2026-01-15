package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idFoyer;
    String nomFoyer;
    long capiciteFoyer;
    @OneToOne(mappedBy = "foyer")
        @JsonIgnore
        @ToString.Exclude
    University university;
    @OneToMany(mappedBy = "foyer")
            @JsonIgnore
            @ToString.Exclude
    Set<Bloc> blocs;


}
