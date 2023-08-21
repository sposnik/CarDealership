package pl.zajavka.DAO;

import pl.zajavka.model.entities.ServiceEntity;

import java.util.Optional;

public interface ServiceDAO {
    Optional<ServiceEntity> findServiceByServiceCode(String serviceCode);
}
