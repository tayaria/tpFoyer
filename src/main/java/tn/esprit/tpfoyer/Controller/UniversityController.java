package tn.esprit.tpfoyer.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.Services.IUniversityService;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.University;
import tn.esprit.tpfoyer.entities.UniversityDTO;

import java.util.List;

@RestController
@RequestMapping("/UnivController")

public class UniversityController {
    @Autowired
    IUniversityService universityService;

    @PostMapping("/addUn")
    University addUniversity(@RequestBody University university) {
        return universityService.addOrUpdateUniversity(university);
    }

    @PutMapping("/updateUn")
    University updateUniversity(@RequestBody University university) {
        return universityService.addOrUpdateUniversity(university);
    }

    @DeleteMapping("/DeleteUn")
    void deleteUniversity(@RequestParam long universityId) {
        universityService.deleteUniversity(universityId);
    }

    @GetMapping("/getAllUn")
    List<University> getAllUn() {
        return universityService.findAllUniversities();
    }

    @GetMapping("/getUn")
    UniversityDTO getUn(@RequestParam long id) {
        return universityService.findUniversityById(id);
    }

    @PutMapping("/assignFToU/{idU}/{idF}")
    University assignFToU(@PathVariable long idU, @PathVariable long idF) {
        return universityService.assignUniversity(idU, idF);
    }

    @PostMapping("/addUnAndAffFoy/{idF}")
    University addUniversityAndAffFoy(@RequestBody University u ,@PathVariable long idF) {
        return  universityService.addUniversityAndAssign(u, idF);
    }

    @GetMapping("/findByAdresse")
    List<University> findByAdresse(@RequestParam String adresse) {
        return universityService.findByAdresse(adresse);
    }

    @PutMapping("/updateAdresseUnv/{idU}/{adresse}")
    int updateAdresseUnv(@PathVariable long idU, @PathVariable String adresse) {
        return universityService.updateAdresseUniversity(adresse,idU);
    }

    @PutMapping("/addFoyandAffectUn/{idU}")
    University addFoyandAffectUn(@RequestBody Foyer foyer , @PathVariable long idU) {
        return universityService.addFoyerAndAffUn(foyer, idU);
    }

}
