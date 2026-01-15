package tn.esprit.tpfoyer.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Services.IEtudiantService;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.entities.EtudiantDTO;
import tn.esprit.tpfoyer.entities.Reservation;

import java.util.List;

@RestController
@RequestMapping("/etudiantController")

public class EtduiantController {
    @Autowired
    IEtudiantService etudiantService;
    @PostMapping("/addEtudiant")
    Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addOrUpdateEtudiant(etudiant);
    }
    @PutMapping("/updateEtudiant")
    Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addOrUpdateEtudiant(etudiant);
    }
    @DeleteMapping("/deleteEtd")
    void deleteEtd(@RequestParam long id) {
        etudiantService.deleteEtudiant(id);
    }
    @GetMapping("/getAllEtd")
    List<EtudiantDTO> getAllEtd() {
        return etudiantService.findAllEtudiants();
    }
    @GetMapping("/getEtd")
    EtudiantDTO getEtd(@RequestParam long id) {
        return etudiantService.findEtudiantById(id);
    }
    @PutMapping("/assignReservation/{idR}/{idE}")
    Reservation assignReservation(@PathVariable String idR, @PathVariable long idE) {
        return etudiantService.assignReservation(idR , idE);
    }

    @GetMapping("/findByNomEt/{nomEt}")
    List<Etudiant> findByNomEt(@PathVariable String nomEt) {
        return etudiantService.findByNomEtContains(nomEt);
    }

    @GetMapping("/countByNomEtContaining/{nomE}")
    long countByNomEtContaining(@PathVariable String nomE) {
        return etudiantService.countByNomEtContaining(nomE);
    }
}
