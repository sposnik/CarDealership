package pl.zajavka.infrastructure.repository.jpaRepositories;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.entities.CarToServiceEntity;
import pl.zajavka.model.CarHistory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CarToServiceJpaRepository extends JpaRepository<CarToServiceEntity, Integer> {

    Optional<CarToServiceEntity> findCarToServiceByVin(String vin);

    void saveCarToService(CarToServiceEntity car);

    CarHistory findHistoryByVin(String vin);
}
