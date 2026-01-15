package tn.esprit.tpfoyer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.University;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
//    List<University> findByFoyerNameFoyerContains(String foyerName);
    @Query(value = "select * from university u where u.adresse_university = :adresse",
            nativeQuery = true)
    List<University> findByAdresse(@Param("adresse") String adresse);
    @Modifying
    @Query("update University u set u.adresseUniversity = :adresse where u.idUniversity = :idUniversity")
    int updateAdresseUniversity(@Param("adresse") String adresse,
                                @Param("idUniversity") Long idUniversity);

}
