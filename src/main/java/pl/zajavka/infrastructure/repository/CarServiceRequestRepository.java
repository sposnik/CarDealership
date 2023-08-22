package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.CarServiceRequestDAO;
import pl.zajavka.infrastructure.entities.CarServiceRequestEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarServiceRequestJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarServiceRequestRepository implements CarServiceRequestDAO {


    private CarServiceRequestJpaRepository carServiceRequestJpaRepository;


    @Override
    public void createServiceRequest(CarServiceRequestEntity request) {
        carServiceRequestJpaRepository.saveAndFlush(request);
    }

    @Override
    public Optional<CarServiceRequestEntity> findRequestByVin(String carVin) {

        Optional<CarServiceRequestEntity> result
                = carServiceRequestJpaRepository.findRequestByVin(carVin);
        return result;
        }

}

