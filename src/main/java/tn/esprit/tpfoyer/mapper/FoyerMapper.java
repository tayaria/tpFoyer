package tn.esprit.tpfoyer.mapper;

import org.mapstruct.Mapper;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.FoyerDTO;

@Mapper(componentModel = "spring")
public interface FoyerMapper {
    FoyerDTO toDto(Foyer foyer);

}
