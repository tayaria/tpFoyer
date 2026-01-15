package tn.esprit.tpfoyer.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Services.IChambreService;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.ChambreDTO;
import tn.esprit.tpfoyer.entities.TypeChambre;
import tn.esprit.tpfoyer.entities.TypeRes;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/chambreController")

public class ChambreController {
    @Autowired
    IChambreService chambreService;
    @PostMapping("/addChambre")
    Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addOrUpdateChambre(chambre);
    }
    @PutMapping("/updateChambre")
    Chambre updateChambre(@RequestBody Chambre chambre) {
        return chambreService.addOrUpdateChambre(chambre);
    }
    @DeleteMapping("/deleteChambre")
    void deleteChambre(@RequestParam long id) {
        chambreService.deleteChambre(id);
    }
    @GetMapping("/getAllChambres")
    List<Chambre> getAllChambres() {
        return chambreService.findAllChambres();
    }
    @GetMapping("/getChambre")
    ChambreDTO getChambre(@RequestParam long id) {
        return chambreService.findChambreById(id);
    }
    @PutMapping("/affecterReservationAChambre")
    Chambre affecterReservationAChambre(@RequestParam Set<String> idReservation, @RequestParam long idChambre) {
        return chambreService.affecterReservationAChambre(idReservation, idChambre);
    }
    @PutMapping("/desaffecterReservationDeChambre")
    Chambre desaffecterReservationDeChambre(@RequestParam String idReservation, @RequestParam long idChambre) {
        return chambreService.desaffecterReservationDeChambre(idReservation, idChambre);
    }

    @GetMapping("/findByTypeC/{t}")
    List<Chambre> findByTypeC(@PathVariable TypeChambre t) {
        return chambreService.findByTypeC(t);
    }

    @GetMapping("/findByNumCh/{num}")
    Chambre findByNumCh(@PathVariable long num) {
        return chambreService.findByNumeroChambre(num);
    }

    @GetMapping("/findChambreByEtudiantCin/{cinE}")
    Chambre findChambreByEtudiantCin(@PathVariable long cinE) {
        return chambreService.findChambreByEtudiantCin(cinE);
    }

}
