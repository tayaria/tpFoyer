package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Repository.FoyerRepository;
import tn.esprit.tpfoyer.Repository.UniversityRepository;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.University;
import tn.esprit.tpfoyer.entities.UniversityDTO;

import java.util.List;
@Service
@AllArgsConstructor
public class UniversityServiceImpl implements IUniversityService{

    final UniversityRepository repo;
    final FoyerRepository foyerRepo;
    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University addOrUpdateUniversity(University university) {
        return repo.save(university);
    }

    @Override
    public void deleteUniversity(long id) {
            repo.deleteById(id);
    }
   // @Scheduled(fixedDelay = 10000)
    @Override
    public List<University> findAllUniversities() {
        return repo.findAll();
    }

    @Override
    public UniversityDTO findUniversityById(long id) {
        University university= repo.findById(id).orElseThrow(()->new RuntimeException("university not found"));
        return convertToDto(university);
    }

    @Override
    public University assignUniversity(long idU, long idF) {
        University university = universityRepository.findById(idU).get();
        Foyer foyer =  foyerRepo.findById(idF).get();
        university.setFoyer(foyer);
        return universityRepository.save(university);
    }

    @Override
    public University addUniversityAndAssign(University university, long idF) {
        Foyer foyer =  foyerRepo.findById(idF).get();
        university.setFoyer(foyer);
        return universityRepository.save(university);
    }

    @Override
    public List<University> findByAdresse(String adresse) {
        return universityRepository.findByAdresse(adresse);
    }

    @Override
    public int updateAdresseUniversity( String adresse, Long idUniversity) {

        return universityRepository.updateAdresseUniversity(adresse , idUniversity);
    }

    @Override
    public University addFoyerAndAffUn(Foyer foyer, long idUniversity) {
        University university = universityRepository.findById(idUniversity).get();
        Foyer f =foyerRepo.save(foyer);
        university.setFoyer(f);
        return universityRepository.save(university);
    }

    private UniversityDTO convertToDto(University university) {
        UniversityDTO dto = new UniversityDTO();
        dto.setAdresseUniversity(university.getAdresseUniversity());
        dto.setNomUniversity(university.getNomUniversity());
        return dto;
    }
}
