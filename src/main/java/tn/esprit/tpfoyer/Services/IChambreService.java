package tn.esprit.tpfoyer.Services;

import org.springframework.data.repository.query.Param;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.ChambreDTO;
import tn.esprit.tpfoyer.entities.TypeChambre;
import tn.esprit.tpfoyer.entities.TypeRes;

import java.util.List;
import java.util.Set;

public interface IChambreService {
    Chambre addOrUpdateChambre(Chambre chambre);
    void deleteChambre(long id);
    List<Chambre> findAllChambres();
    ChambreDTO findChambreById(long id);
    Chambre affecterReservationAChambre(Set<String> idReservation, long idChambre);
    Chambre desaffecterReservationDeChambre(String idReservation, long idChambre);
    List<Chambre> findByTypeC(TypeChambre typeC);
    Chambre findByNumeroChambre(long numeroChambre);
    Chambre findChambreByEtudiantCin(long cin);

}
