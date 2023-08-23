package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.MechanicDAO;
import pl.zajavka.infrastructure.repository.jpaRepositories.MechanicJpaRepository;
import pl.zajavka.infrastructure.repository.mapper.MechanicEntityMapper;
import pl.zajavka.model.Mechanic;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MechanicRepository implements MechanicDAO {

    private MechanicJpaRepository mechanicJpaRepository;
    private MechanicEntityMapper mechanicEntityMapper;

    @Override
    public Optional<Mechanic> findByPesel(String pesel) {
        return mechanicJpaRepository.findByPesel(pesel)
                .map(mechanicEntityMapper::mapFromEntity);
    }
}
