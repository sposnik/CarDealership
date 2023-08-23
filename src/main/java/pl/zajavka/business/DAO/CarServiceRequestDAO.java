package pl.zajavka.business.DAO;

import pl.zajavka.model.CarServiceRequest;

import java.util.Optional;

public interface CarServiceRequestDAO {
    void createServiceRequest(CarServiceRequest carServiceRequest);

    Optional<CarServiceRequest> findRequestByVin(String carVin);
}
