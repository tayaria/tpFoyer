package tn.esprit.tpfoyer.mapper;

import org.mapstruct.Mapper;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.ChambreDTO;

@Mapper(componentModel = "spring")
public interface ChambreMapper {
    ChambreDTO toDTO(Chambre chambre);
}
