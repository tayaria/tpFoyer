package tn.esprit.tpfoyer.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tn.esprit.tpfoyer.entities.EtudiantDTO;
import tn.esprit.tpfoyer.entities.Etudiant;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EtudiantMapper {

    EtudiantDTO toDto(Etudiant etudiant);

    List<EtudiantDTO> toDtoList(List<Etudiant> etudiants);
}
