package pl.zajavka.infrastructure.repository.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.entities.ServiceEntity;

import java.util.Optional;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Integer> {

    Optional<ServiceEntity> findServiceByServiceCode(String serviceCode);
}