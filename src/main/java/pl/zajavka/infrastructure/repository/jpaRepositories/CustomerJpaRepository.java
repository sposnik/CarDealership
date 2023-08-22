package pl.zajavka.infrastructure.repository.jpaRepositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.entities.CustomerEntity;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {

    Optional<CustomerEntity> findByEmail(String email);

}
