package tn.esprit.tpfoyer.entities;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EtudiantDTO {
    private String nomEt;
    private String prenomEt;
    private String ecole;
    private Date dateNaissance;
}