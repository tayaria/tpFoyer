package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Repository.BlocRepository;
import tn.esprit.tpfoyer.Repository.FoyerRepository;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.BlocDTO;
import tn.esprit.tpfoyer.entities.Foyer;

import java.util.List;
@AllArgsConstructor
@Service

public class BlocServiceImpl implements IBlocService {

    final BlocRepository blocRepository;
    final FoyerRepository foyerRepository;
    @Override
    public Bloc addOrUpdateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBloc(long id) {
            blocRepository.deleteById(id);
    }

    @Override
    public List<Bloc> findAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public BlocDTO findBlocById(long id) {
        Bloc bloc=blocRepository.findById(id).orElseThrow(()->new RuntimeException("Bloc not found"));
        return convertToDto(bloc);
    }

    @Override
    public void assignBlocToFoyer(long idBloc, long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        Bloc bloc = blocRepository.findById(idBloc).get();
        bloc.setFoyer(foyer);
        blocRepository.save(bloc);
    }

    @Override
    public Bloc desaffecterBlocFromoFoyer(long idBloc) {

        Bloc bloc = blocRepository.findById(idBloc).get();
        bloc.setFoyer(null);
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> findBlocByFoyerNull() {
        return blocRepository.findBlocByFoyerNull();
    }

    @Override
    public List<Bloc> findByCapaciteBlocGreaterThan(long capaciteBloc) {
        return blocRepository.findByCapaciteBlocGreaterThan(capaciteBloc);
    }

    @Override
    public List<Bloc> findByNomBlocStartsWith(String nomBloc) {
        return blocRepository.findByNomBlocStartsWith(nomBloc);
    }

    @Override
    public List<Bloc> findByNomBlocStartsWithAndCapaciteBlocGreaterThan(String nomBloc, long capaciteBloc) {
        return blocRepository.findByNomBlocStartsWithAndCapaciteBlocGreaterThan(nomBloc, capaciteBloc);
    }


    private BlocDTO convertToDto(Bloc bloc) {
        BlocDTO dto = new BlocDTO();
        dto.setCapaciteBloc(bloc.getCapaciteBloc());
        dto.setNomBloc(bloc.getNomBloc());
        return dto;
    }



}
