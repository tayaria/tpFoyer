package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Repository.EtudiantRepository;
import tn.esprit.tpfoyer.Repository.ReservationRepository;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.entities.EtudiantDTO;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.mapper.EtudiantMapper;

import java.util.List;
@Service
@AllArgsConstructor
public class EtduiantServiceImpl implements IEtudiantService{

    final EtudiantRepository etudiantRepository;
    final EtudiantMapper mapper;
    final ReservationRepository resRepo;
    private final ReservationRepository reservationRepository;

    @Override
    public Etudiant addOrUpdateEtudiant(Etudiant et) {
        return etudiantRepository.save(et);
    }

    @Override
    public void deleteEtudiant(long id) {
            etudiantRepository.deleteById(id);
    }

    @Override
    public List<EtudiantDTO> findAllEtudiants() {
        List<Etudiant> etudiants= etudiantRepository.findAll();
        return mapper.toDtoList(etudiants);
    }

    @Override
    public EtudiantDTO findEtudiantById(long id) {

        Etudiant et =  etudiantRepository.findById(id).orElseThrow(null);
        return  mapper.toDto(et);
    }

    @Override
    public Reservation assignReservation(String idR, long idE) {
        Reservation reservation = reservationRepository.findById(idR).get();
        Etudiant et = etudiantRepository.findById(idE).get();
        reservation.getEtudiants().add(et);
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Etudiant> findByNomEtContains(String nom) {
        return etudiantRepository.findByNomEtContaining(nom);
    }

    @Override
    public long countByNomEtContaining(String nom) {
        return etudiantRepository.countByNomEtContaining(nom);
    }


}
