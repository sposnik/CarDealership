package pl.zajavka.business.DAO;

import pl.zajavka.model.CarHistory;
import pl.zajavka.model.CarToService;

import java.util.Optional;

public interface CarToServiceDAO {

    Optional<CarToService> findCarToServiceByVin(String vin);

    CarToService saveCarToService(CarToService car);

    CarHistory findHistoryByVin(String vin);

}
