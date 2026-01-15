package tn.esprit.tpfoyer.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Services.IReservationService;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.ReservationDTO;

import java.util.List;

@RestController
@RequestMapping("/reservationController")

public class ReservationController {
    @Autowired
    IReservationService reservationService;
    @PostMapping("/addRes")
    Reservation addRes(@RequestBody Reservation reservation) {
        return reservationService.addOrUpdateReservation(reservation);
    }
    @PutMapping("/updateRes")
    Reservation updateRes(@RequestBody Reservation reservation) {
        return reservationService.addOrUpdateReservation(reservation);
    }

    @DeleteMapping("/deleteRes")
    void deleteRes(@RequestParam String id) {
        reservationService.deleteReservation(id);
    }

    @GetMapping("/getAllRes")
    List<Reservation> getAllRes() {
        return reservationService.findAllReservations();
    }
    @GetMapping("/getReservationByID/{idR}")
    Reservation getReservationByID(@PathVariable String idR) {
        return  reservationService.findReservationById(idR);
    }

    @GetMapping("/getRes")
    ReservationDTO getRes(@RequestParam String id) {
        return reservationService.findReservation(id);
    }

}
