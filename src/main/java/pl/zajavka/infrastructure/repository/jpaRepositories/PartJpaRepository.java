package pl.zajavka.infrastructure.repository.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.entities.PartEntity;

import java.util.Optional;

@Repository
public interface PartJpaRepository extends JpaRepository<PartEntity, Integer> {

    Optional<PartEntity> findBySerialNumber(String serialNumber);

}
