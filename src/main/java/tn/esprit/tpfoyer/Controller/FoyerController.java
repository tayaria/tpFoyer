package tn.esprit.tpfoyer.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Services.IFoyerService;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.FoyerDTO;

import java.util.List;

@RestController
@RequestMapping("/foyerController")
//@Tag(name = "foyer")

public class FoyerController {
    @Autowired
    IFoyerService foyerService;
    @PostMapping("/addFoyer")
    Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addOrUpdateFoyer(foyer);
    }
    @PutMapping("/updateFoyer")
    Foyer updateFoyer(@RequestBody Foyer foyer) {
        return foyerService.addOrUpdateFoyer(foyer);
    }
    @DeleteMapping("/deleteFoyer")
    void deleteFoyer(@RequestParam long id) {
        foyerService.deleteFoyer(id);
    }

    @GetMapping("/getAllFoyer")
    List<Foyer> getAllFoyer() {
        return foyerService.getAllFoyers();
    }
    //@Operation(description = "")
    @GetMapping("/getFoyer")
    FoyerDTO getFoyer(@RequestParam long id) {
        return foyerService.findFoyerById(id);
    }
    @PostMapping("/addFoyAndBloc")
    @ResponseBody
    Foyer addFoyAndBloc(@RequestBody Foyer foyer) {
        return foyerService.addFoyerAndBloc(foyer);
    }

    @GetMapping("/findByNomFoyer/{t}")
    List<Foyer> findByNomFoyer(@PathVariable String t) {
        return  foyerService.findByNomFoyerContains(t);
    }

    @GetMapping("/findByNomFoyer")
    List<Foyer> findFoyerByNomFoyer(@RequestParam String nomFoyer) {
        return foyerService.findByNomFoyer(nomFoyer);
    }

    @GetMapping("/findByNomBloc")
    List<Foyer> findByNomBloc(@RequestParam String nomBloc) {
        return foyerService.findByNomBloc(nomBloc);
    }
}
