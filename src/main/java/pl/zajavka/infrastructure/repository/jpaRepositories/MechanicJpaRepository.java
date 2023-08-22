package pl.zajavka.infrastructure.repository.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.entities.MechanicEntity;

import java.util.Optional;

@Repository
public interface MechanicJpaRepository extends JpaRepository<MechanicEntity, Integer> {

    Optional<MechanicEntity> findByPesel(String pesel);

}