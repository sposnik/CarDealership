package pl.zajavka.business.managment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.CarToBuyDAO;
import pl.zajavka.business.DAO.CarToServiceDAO;
import pl.zajavka.infrastructure.entities.CarToBuyEntity;
import pl.zajavka.infrastructure.entities.CarToServiceEntity;
import pl.zajavka.model.CarHistory;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CarService {

    private CarToServiceDAO carToServiceDAO;

    private CarToBuyDAO carToBuyDAO;

    public CarToBuyEntity findCarToBuy(String vin) {
        Optional<CarToBuyEntity> carToBuyByVin = carToBuyDAO.findCarToBuyByVin(vin);
        if (carToBuyByVin.isEmpty()) {
            throw new RuntimeException("Could not find car by vin: [%s]".formatted(vin));
        }
        return carToBuyByVin.get();
    }

    public Optional<CarToServiceEntity> findCarToService(String vin) {
        return carToServiceDAO.findCarToServiceByVin(vin);
    }

    public void saveCarToService (CarToServiceEntity car){
        carToServiceDAO.saveCarToService(car);
    }

    public CarToServiceEntity copyCarFromBuyToService(CarToServiceEntity carToService) {
        CarToBuyEntity carToBuy = findCarToBuy(carToService.getVin());
        return CarToServiceEntity.builder()
                .vin(carToBuy.getVin())
                .brand(carToBuy.getBrand())
                .model(carToBuy.getModel())
                .year(carToBuy.getYear())
                .build();
    }

    public void showCarHistory(String vin) {
        CarHistory carHistory = carToServiceDAO.findHistoryByVin(vin);
        log.info("CAR HISTORY ( [{}] ): ", vin);
        carHistory.getServiceRequests().forEach(this::printRequests);
    }

    private void printRequests(CarHistory.ServiceRequest serviceRequest) {
        log.info("SERVICE REQUEST ( [{}] ):", serviceRequest);
        serviceRequest.services().forEach(service -> log.info("SERVICE ( [{}] ):", service));
        serviceRequest.parts().forEach(part -> log.info("PART ( [{}] ):", part));
    }



}
