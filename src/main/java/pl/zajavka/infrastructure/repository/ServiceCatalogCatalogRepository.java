package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.ServiceCatalogDAO;
import pl.zajavka.infrastructure.repository.jpaRepositories.ServiceCatalogJpaRepository;
import pl.zajavka.infrastructure.repository.mapper.ServiceCatalogEntityMapper;
import pl.zajavka.model.ServiceCatalog;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ServiceCatalogCatalogRepository implements ServiceCatalogDAO {

    ServiceCatalogJpaRepository serviceCatalogJpaRepository;

    ServiceCatalogEntityMapper serviceCatalogEntityMapper;

    @Override
    public Optional<ServiceCatalog> findServiceByServiceCode(String serviceCode) {
        return serviceCatalogJpaRepository.findServiceByServiceCode(serviceCode)
                .map(serviceCatalogEntityMapper::mapFromEntity);
    }
}
