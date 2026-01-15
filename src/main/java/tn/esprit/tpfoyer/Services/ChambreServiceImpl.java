package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Repository.BlocRepository;
import tn.esprit.tpfoyer.Repository.ChambreRepository;
import tn.esprit.tpfoyer.Repository.ReservationRepository;
import tn.esprit.tpfoyer.entities.*;
import tn.esprit.tpfoyer.mapper.ChambreMapper;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    final ChambreRepository chambreRepository;
    final ChambreMapper chambreMapper;
    final ReservationRepository reservationRepository;
    final BlocRepository blocRepository;
    @Override
    public Chambre addOrUpdateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public List<Chambre> findAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public ChambreDTO findChambreById(long id) {
        Chambre chambre = chambreRepository.findById(id).orElseThrow(null);
        return chambreMapper.toDTO(chambre);
    }

    @Override
    public Chambre affecterReservationAChambre(Set<String> idReservation, long idChambre) {
        List<Reservation> reservation = reservationRepository.findByTypeResIn(idReservation);
        Chambre chambre = chambreRepository.findById(idChambre).get();
        chambre.getReservations().addAll(reservation);
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre desaffecterReservationDeChambre(String idReservation, long idChambre) {
        Chambre chambre = chambreRepository.findById(idChambre).get();
        Reservation reservation = reservationRepository.findById(idReservation).get();
        chambre.getReservations().remove(reservation);
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> findByTypeC(TypeChambre typeC) {
        return chambreRepository.findByTypeCLike(typeC);
    }

    @Override
    public Chambre findByNumeroChambre(long numeroChambre) {
        return chambreRepository.findByNumeroChambre(numeroChambre);
    }

    @Override
    public Chambre findChambreByEtudiantCin(long cin) {
        return chambreRepository.findChambreByEtudiantCin(cin);
    }

////////////////////////////////////////////////////////////////////////////////////////////////
@Scheduled(cron = "0 * * * * *")
public void listeChambresParBloc() {

    for (Bloc bloc : blocRepository.findAllWithChambres()) {

        log.info("Bloc => " + bloc.getNomBloc() + " ayant une capacite " + bloc.getCapaciteBloc());

        if (bloc.getChambres() == null || bloc.getChambres().isEmpty()) {
            log.info("Pas de chambre disponible dans ce bloc");
        } else {
            log.info("La liste des chambres pour ce bloc:");
            for (Chambre c : bloc.getChambres()) {
                log.info("NumChambre: " +c.getNumeroChambre() + " type: " + c.getTypeC());
            }
        }

        log.info("********************");
    }
}


    @Scheduled(cron = "0 */5 * * * *")
    public void pourcentageChambreParTypeChambre() {
        long total = chambreRepository.count();

        if (total == 0) {
            log.info("Nombre total des chambres : 0");
            log.info("Le pourcentage des chambres pour le type SIMPLE est egale a 0.0");
            log.info("Le pourcentage des chambres pour le type DOUBLE est egale a 0.0");
            log.info("Le pourcentage des chambres pour le type TRIPLE est egale a 0.0");
            return;
        }

        long simple = chambreRepository.countByTypeC(TypeChambre.SIMPLE);
        long doub = chambreRepository.countByTypeC(TypeChambre.DOUBLE);
        long triple = chambreRepository.countByTypeC(TypeChambre.TRIPLE);

        log.info("Nombre total des chambres : " + total );

        log.info("Le pourcentage des chambres pour le type SIMPLE est egale a " + (simple * 100.0 / total));
        log.info("Le pourcentage des chambres pour le type DOUBLE est egale a " + (doub * 100.0 / total));
        log.info("Le pourcentage des chambres pour le type TRIPLE est egale a " + (triple * 100.0 / total));
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void nbPlacesDisponibleParChambreAnneeEnCours() {

        int anneeEnCours = LocalDate.now().getYear();

        List<Chambre> chambres = chambreRepository.findAllWithReservations();

        for (Chambre c : chambres) {

            int capacite = switch (c.getTypeC()) {
                case SIMPLE -> 1;
                case DOUBLE -> 2;
                case TRIPLE -> 3;
            };


            long nbReservationsAnnee =
                    (c.getReservations() == null) ? 0 :
                            c.getReservations().stream()
                                    .filter(r -> r.getAnneeUniversitaire() != null)
                                    .filter(r -> r.getAnneeUniversitaire()
                                            .toInstant()
                                            .atZone(ZoneId.systemDefault())
                                            .getYear() == anneeEnCours)
                                    .count();

            int placesDispo = capacite - (int) nbReservationsAnnee;

            if (placesDispo <= 0) {
                log.info("La chambre " + c.getTypeC() + " " + c.getNumeroChambre() + " est complete");
            } else {
                log.info("Le nombre de place disponible pour la chambre " + c.getTypeC() + c.getNumeroChambre() + " est " + placesDispo );
            }
        }
    }



}
