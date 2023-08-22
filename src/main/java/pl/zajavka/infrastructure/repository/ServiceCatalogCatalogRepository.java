package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.ServiceCatalogDAO;
import pl.zajavka.infrastructure.entities.ServiceCatalogEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.ServiceCatalogJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ServiceCatalogCatalogRepository implements ServiceCatalogDAO {

    ServiceCatalogJpaRepository serviceCatalogJpaRepository;

    @Override
    public Optional<ServiceCatalogEntity> findServiceByServiceCode(String serviceCode) {
        return serviceCatalogJpaRepository.findServiceByServiceCode(serviceCode);
    }
}
