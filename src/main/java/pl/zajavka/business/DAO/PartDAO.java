package pl.zajavka.business.DAO;

import pl.zajavka.infrastructure.entities.PartEntity;

import java.util.Optional;

public interface PartDAO {

    Optional<PartEntity> findBySerialNumber(String serialNumber);

}
