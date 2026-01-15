package tn.esprit.tpfoyer.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.Repository.BlocRepository;
import tn.esprit.tpfoyer.Repository.FoyerRepository;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.FoyerDTO;
import tn.esprit.tpfoyer.mapper.FoyerMapper;

import java.util.List;
@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService{

    final FoyerRepository foyerRepository;
    final FoyerMapper foyerMapper;
    final BlocRepository blocRepository;
    @Override
    public Foyer addOrUpdateFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public void deleteFoyer(long id) {
            foyerRepository.deleteById(id);
    }

    @Override
    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public FoyerDTO findFoyerById(long id) {
        Foyer foyer = foyerRepository.findById(id).orElseThrow(null);
        return foyerMapper.toDto(foyer);

    }

    @Override
    public Foyer addFoyerAndBloc(Foyer foyer) {
        Foyer f = foyerRepository.save(foyer);
        f.getBlocs().forEach(bloc -> {
            bloc.setFoyer(f);
            blocRepository.save(bloc);
        });
        return f;
    }

    @Override
    public List<Foyer> findByNomFoyerContains(String nomFoyer) {
        return foyerRepository.findByNomFoyerContains(nomFoyer);
    }

    @Override
    public List<Foyer> findByNomFoyer(String nomFoyer) {
        return foyerRepository.findByNomFoyer(nomFoyer);
    }

    @Override
    public List<Foyer> findByNomBloc(String nomBloc) {
        return foyerRepository.findByNomBloc(nomBloc);
    }


}
