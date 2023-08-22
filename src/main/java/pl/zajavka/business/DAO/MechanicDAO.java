package pl.zajavka.business.DAO;

import pl.zajavka.infrastructure.entities.MechanicEntity;

import java.util.Optional;

public interface MechanicDAO {

    Optional<MechanicEntity> findByPesel(String pesel);

}
