package tn.esprit.tpfoyer.Services;

import org.springframework.data.repository.query.Param;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.FoyerDTO;

import java.util.List;

public interface IFoyerService {
    Foyer addOrUpdateFoyer(Foyer foyer);
    void deleteFoyer(long id);
    List<Foyer> getAllFoyers();
    FoyerDTO findFoyerById(long id);
    Foyer addFoyerAndBloc(Foyer foyer);
    List<Foyer> findByNomFoyerContains(String nomFoyer);
    List<Foyer> findByNomFoyer( String nomFoyer);
    List<Foyer> findByNomBloc(@Param("nomBloc") String nomBloc);

}
