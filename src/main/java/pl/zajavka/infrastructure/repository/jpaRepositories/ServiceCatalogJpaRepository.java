package pl.zajavka.infrastructure.repository.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.entities.ServiceCatalogEntity;

import java.util.Optional;

@Repository
public interface ServiceCatalogJpaRepository extends JpaRepository<ServiceCatalogEntity, Integer> {

    Optional<ServiceCatalogEntity> findServiceByServiceCode(String serviceCode);
}