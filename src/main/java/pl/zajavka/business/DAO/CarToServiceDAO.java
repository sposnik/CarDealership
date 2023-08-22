package pl.zajavka.business.DAO;

import pl.zajavka.model.CarHistory;
import pl.zajavka.infrastructure.entities.CarToServiceEntity;

import java.util.Optional;

public interface CarToServiceDAO {


    Optional<CarToServiceEntity> findCarToServiceByVin(String vin);

    void saveCarToService(CarToServiceEntity car);

    CarHistory findHistoryByVin(String vin);

//    void findHistoryByVin2(String vin);
}
