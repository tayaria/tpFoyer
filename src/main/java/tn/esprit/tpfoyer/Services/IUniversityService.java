package tn.esprit.tpfoyer.Services;

import org.springframework.data.repository.query.Param;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.University;
import tn.esprit.tpfoyer.entities.UniversityDTO;

import java.util.List;

public interface IUniversityService {
    University addOrUpdateUniversity(University university);
    void deleteUniversity(long id);
    List<University> findAllUniversities();
    UniversityDTO findUniversityById(long id);
    University assignUniversity(long idU , long idF);
    University addUniversityAndAssign(University university , long idF);
    List<University> findByAdresse( String adresse);
    int updateAdresseUniversity (String adresse ,Long idUniversity);
    University addFoyerAndAffUn(Foyer foyer , long idUniversity);

}
