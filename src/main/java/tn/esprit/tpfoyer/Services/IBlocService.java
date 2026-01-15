package tn.esprit.tpfoyer.Services;

import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.BlocDTO;

import java.util.List;

public interface IBlocService {
    Bloc addOrUpdateBloc(Bloc bloc);
    void deleteBloc(long id);
    List<Bloc> findAllBlocs();
    BlocDTO findBlocById(long id);
    void assignBlocToFoyer(long idBloc,long idFoyer);

    Bloc desaffecterBlocFromoFoyer(long idBloc);
    List<Bloc> findBlocByFoyerNull();
    List<Bloc> findByCapaciteBlocGreaterThan(long capaciteBloc);
    List<Bloc> findByNomBlocStartsWith(String nomBloc);
    List<Bloc> findByNomBlocStartsWithAndCapaciteBlocGreaterThan(String nomBloc, long capaciteBloc);
}
