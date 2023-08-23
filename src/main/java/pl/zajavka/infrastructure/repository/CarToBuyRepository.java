package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.CarToBuyDAO;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarToBuyJpaRepository;
import pl.zajavka.infrastructure.repository.mapper.CarToBuyEntityMapper;
import pl.zajavka.model.CarToBuy;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarToBuyRepository implements CarToBuyDAO {

    private CarToBuyJpaRepository carToBuyJpaRepository;

    private CarToBuyEntityMapper carToBuyEntityMapper;

    @Override
    public Optional<CarToBuy> findCarToBuyByVin(String vin) {
        return carToBuyJpaRepository.findCarToBuyByVin(vin)
                .map(carToBuyEntityMapper::mapFromEntity);
    }
}
