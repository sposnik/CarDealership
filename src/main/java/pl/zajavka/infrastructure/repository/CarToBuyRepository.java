package pl.zajavka.infrastructure.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.DAO.CarToBuyDAO;
import pl.zajavka.infrastructure.entities.CarToBuyEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarToBuyJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarToServiceJpaRepository;

import java.util.Optional;

public class CarToBuyRepository implements CarToBuyDAO {

    private CarToBuyJpaRepository carToBuyJpaRepository;
    @Override
    public Optional<CarToBuyEntity> findCarToBuyByVin(String vin) {
        return carToBuyJpaRepository.findCarToBuyByVin(vin);
    }
}
