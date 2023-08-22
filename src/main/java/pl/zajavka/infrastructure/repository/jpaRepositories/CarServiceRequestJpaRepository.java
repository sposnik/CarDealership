package pl.zajavka.infrastructure.repository.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.entities.CarServiceRequestEntity;
import pl.zajavka.model.CarServiceRequest;

import java.util.Optional;

@Repository
public interface CarServiceRequestJpaRepository extends JpaRepository<CarServiceRequestEntity, Integer> {

    @Query("""
     SELECT rq FROM CarServiceRequestEntity rq WHERE rq.car.vin = :vin
     AND rq.completedDateTime is NULL
     """)
    Optional<CarServiceRequestEntity> findRequestByVin(final @Param("vin") String carVin);

}