package tn.esprit.tpfoyer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Foyer;

import java.util.List;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {

    List<Foyer> findByNomFoyerContains(String nomFoyer);
    @Query("select f from Foyer f where f.nomFoyer =:nomFoyer ")
    List<Foyer> findByNomFoyer(@Param("nomFoyer") String nomFoyer);
    @Query("select foy from Foyer foy join foy.blocs bloc where bloc.nomBloc = :nomBloc")
    List<Foyer> findByNomBloc(@Param("nomBloc") String nomBloc);
}
