package pl.zajavka.business.DAO;

import pl.zajavka.infrastructure.entities.CarServiceRequestEntity;

import java.util.Optional;

public interface CarServiceRequestDAO {
    void createServiceRequest(CarServiceRequestEntity carServiceRequestEntity);

    Optional<CarServiceRequestEntity> findRequestByVin(String carVin);
}
