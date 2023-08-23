package pl.zajavka.business.DAO;

import pl.zajavka.model.ServiceCatalog;

import java.util.Optional;

public interface ServiceCatalogDAO {
    Optional<ServiceCatalog> findServiceByServiceCode(String serviceCode);
}
