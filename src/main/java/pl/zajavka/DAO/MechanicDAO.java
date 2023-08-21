package pl.zajavka.DAO;

import pl.zajavka.model.entities.MechanicEntity;

import java.util.Optional;

public interface MechanicDAO {

    Optional<MechanicEntity> findByPesel(String pesel);

}
