package tn.esprit.tpfoyer.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Services.IBlocService;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.BlocDTO;

import java.util.List;

@RestController
@RequestMapping("/blocController")

public class BlocController {
    @Autowired
   IBlocService blocService;
    @PostMapping("/addBloc")
    Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addOrUpdateBloc(bloc);
    }
    @PutMapping("/UpdateBloc")
    Bloc updateBloc(@RequestBody Bloc bloc) {
        return blocService.addOrUpdateBloc(bloc);
    }
    @DeleteMapping("/DeleteUpdate")
    void deleteBloc(@RequestParam long id) {
        blocService.deleteBloc(id);
    }

    @GetMapping("/getAllBlocs")
    List<Bloc> getAllBloc() {
        return blocService.findAllBlocs();
    }
    @GetMapping("/getBloc")
    BlocDTO getBloc(@RequestParam long id) {
        return blocService.findBlocById(id);
    }

    @PutMapping("/assignBlocToFoyer/{idB}/{idF}")
    void assignBlocToFoyer(@PathVariable long idB, @PathVariable long idF) {
        blocService.assignBlocToFoyer(idB, idF);
    }

    @PutMapping("/desaffecterBlocFromFoyer/{idB}")
    Bloc desaffecterBlocFromFoyer(@PathVariable long idB) {
            return blocService.desaffecterBlocFromoFoyer(idB);
    }

    @GetMapping("/findByFoyerNull")
    List<Bloc> findByFoyerNull() {
        return blocService.findBlocByFoyerNull();
    }
    @GetMapping("/findByCapaciteBlocSup30")
    List<Bloc> findByCapaciteBlocSup30() {
        return blocService.findByCapaciteBlocGreaterThan(30);
    }

    @GetMapping("/findByNomBlocStaringWithA")
    List<Bloc> findByNomBlocStaringWithA() {
        return blocService.findByNomBlocStartsWith("A");
    }

    @GetMapping("/findByNomStartsWithAAndCapaciteSup30")
    List<Bloc> findByNomStartsWithAAndCapaciteSup30() {
        return blocService.findByNomBlocStartsWithAndCapaciteBlocGreaterThan("A",30);
    }


}
