package pl.zajavka.business.DAO;

import pl.zajavka.infrastructure.entities.ServiceCatalogEntity;

import java.util.Optional;

public interface ServiceCatalogDAO {
    Optional<ServiceCatalogEntity> findServiceByServiceCode(String serviceCode);
}
