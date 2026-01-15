package tn.esprit.tpfoyer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Bloc;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
    List<Bloc> findBlocByFoyerNull();
    List<Bloc> findByCapaciteBlocGreaterThan(long capaciteBloc);
    List<Bloc> findByNomBlocStartsWith(String nomBloc);
    List<Bloc> findByNomBlocStartsWithAndCapaciteBlocGreaterThan(String nomBloc, long capaciteBloc);
    @Query("SELECT b FROM Bloc b LEFT JOIN FETCH b.chambres")
    List<Bloc> findAllWithChambres();

}
