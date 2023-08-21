package pl.zajavka.DAO;

import pl.zajavka.model.entities.CarServiceRequestEntity;

import java.util.Optional;

public interface CarServiceRequestDAO {
    void createServiceRequest(CarServiceRequestEntity carServiceRequestEntity);

    Optional<CarServiceRequestEntity> findRequestByVin(String carVin);
}
