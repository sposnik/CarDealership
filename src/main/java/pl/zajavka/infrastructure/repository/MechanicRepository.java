package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.MechanicDAO;
import pl.zajavka.infrastructure.entities.MechanicEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.MechanicJpaRepository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class MechanicRepository implements MechanicDAO {

    private MechanicJpaRepository mechanicJpaRepository;
    @Override
    public Optional<MechanicEntity> findByPesel(String pesel) {

        Optional<MechanicEntity> result = mechanicJpaRepository.findByPesel(pesel);
            return result;
    }
}
