package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.CarServiceRequestDAO;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarServiceRequestJpaRepository;
import pl.zajavka.infrastructure.repository.mapper.CarServiceRequestEntityMapper;
import pl.zajavka.model.CarServiceRequest;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarServiceRequestRepository implements CarServiceRequestDAO {

    private CarServiceRequestJpaRepository carServiceRequestJpaRepository;

    private CarServiceRequestEntityMapper carServiceRequestEntityMapper;


    @Override
    public void createServiceRequest(CarServiceRequest request) {
        carServiceRequestJpaRepository.saveAndFlush(carServiceRequestEntityMapper.mapToEntity(request));
    }

    @Override
    public Optional<CarServiceRequest> findRequestByVin(String carVin) {
        return carServiceRequestJpaRepository.findRequestByVin(carVin)
                .map(carServiceRequestEntityMapper::mapFromEntity);
    }
}

