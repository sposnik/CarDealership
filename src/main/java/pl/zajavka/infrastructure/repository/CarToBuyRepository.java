package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.CarToBuyDAO;
import pl.zajavka.infrastructure.entities.CarToBuyEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarToBuyJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarToBuyRepository implements CarToBuyDAO {


    private CarToBuyJpaRepository carToBuyJpaRepository;
    @Override
    public Optional<CarToBuyEntity> findCarToBuyByVin(String vin) {
        return carToBuyJpaRepository.findCarToBuyByVin(vin);
    }
}
