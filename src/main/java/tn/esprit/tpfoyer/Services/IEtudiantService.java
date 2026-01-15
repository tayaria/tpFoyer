package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.entities.EtudiantDTO;
import tn.esprit.tpfoyer.entities.Reservation;

import java.util.List;

public interface IEtudiantService {
    Etudiant addOrUpdateEtudiant(Etudiant et);
    void deleteEtudiant(long id);
    List<EtudiantDTO> findAllEtudiants();
    EtudiantDTO findEtudiantById(long id);
    Reservation assignReservation(String idR , long idE);

    List<Etudiant> findByNomEtContains(String nom);
    long countByNomEtContaining(String nom);

}
