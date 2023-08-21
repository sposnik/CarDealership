package pl.zajavka.DAO;

import pl.zajavka.model.entities.MechanicEntity;
import pl.zajavka.model.entities.PartEntity;

import java.util.Optional;

public interface PartDAO {

    Optional<PartEntity> findBySerialNumber(String serialNumber);

}
