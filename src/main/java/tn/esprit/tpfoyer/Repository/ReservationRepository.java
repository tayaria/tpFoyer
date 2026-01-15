package tn.esprit.tpfoyer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.TypeRes;

import java.util.List;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findByTypeResIn(Set<String> types);
}
