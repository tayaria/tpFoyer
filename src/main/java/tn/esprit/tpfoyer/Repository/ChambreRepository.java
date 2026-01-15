package tn.esprit.tpfoyer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    List<Chambre> findByTypeCLike(TypeChambre typeC);
    Chambre findByNumeroChambre(long numeroChambre);

    @Query("SELECT c FROM Chambre c JOIN c.reservations r JOIN r.etudiants e WHERE e.cin = :cin")
    Chambre findChambreByEtudiantCin(@Param("cin") long cin);

    long countByTypeC(TypeChambre typeC);

    @Query("SELECT c FROM Chambre c LEFT JOIN FETCH c.reservations")
    List<Chambre> findAllWithReservations();


}
