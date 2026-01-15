package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.ReservationDTO;

import java.util.List;

public interface IReservationService {
    Reservation addOrUpdateReservation(Reservation reservation);
    void deleteReservation(String id);
    List<Reservation> findAllReservations();
    Reservation findReservationById(String id);
    ReservationDTO findReservation(String id);

}
