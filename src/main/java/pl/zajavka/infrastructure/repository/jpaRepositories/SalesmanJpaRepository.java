package pl.zajavka.infrastructure.repository.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.entities.SalesmanEntity;

import java.util.Optional;

@Repository
public interface SalesmanJpaRepository extends JpaRepository<SalesmanEntity, Integer> {

    Optional<SalesmanEntity> findByPesel (String pesel);
}
