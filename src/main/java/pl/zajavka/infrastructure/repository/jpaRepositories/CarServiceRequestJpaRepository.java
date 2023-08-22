package pl.zajavka.infrastructure.repository.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.model.CarServiceRequest;

@Repository
public interface CarServiceRequestJpaRepository extends JpaRepository<CarServiceRequest, Integer> {

}