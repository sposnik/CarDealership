package pl.zajavka.business.DAO;

import pl.zajavka.model.Mechanic;

import java.util.Optional;

public interface MechanicDAO {

    Optional<Mechanic> findByPesel(String pesel);

}
