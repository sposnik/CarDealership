package pl.zajavka.business.DAO;

import pl.zajavka.infrastructure.entities.ServiceEntity;

import java.util.Optional;

public interface ServiceDAO {
    Optional<ServiceEntity> findServiceByServiceCode(String serviceCode);
}
