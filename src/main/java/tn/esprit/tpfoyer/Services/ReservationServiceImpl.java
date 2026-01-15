package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Repository.ReservationRepository;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.ReservationDTO;


import java.util.List;
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService{

    final ReservationRepository reservationRepository;
    @Override
    public Reservation addOrUpdateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(String id) {
            reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findReservationById(String id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public ReservationDTO findReservation(String id) {
       Reservation reservation= reservationRepository.findById(id).orElseThrow(()->new RuntimeException("Reservation not found"));
       return convertToDTO(reservation);
    }



    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setAnneeUniversitaire(reservation.getAnneeUniversitaire());
        reservationDTO.setEstValide(reservation.isEstValide());
        return reservationDTO;
    }
}
