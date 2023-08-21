package pl.zajavka.DAO;

import pl.zajavka.model.entities.CarHistory;
import pl.zajavka.model.entities.CarToBuyEntity;
import pl.zajavka.model.entities.CarToServiceEntity;

import java.util.Optional;

public interface CarDAO {

    Optional<CarToBuyEntity> findCarToBuyByVin(String vin);
    Optional<CarToServiceEntity> findCarToServiceByVin(String vin);

    void saveCarToService(CarToServiceEntity car);

    CarHistory findHistoryByVin(String vin);

    void findHistoryByVin2(String vin);
}
