package pl.zajavka.managment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.zajavka.DAO.CarDAO;
import pl.zajavka.model.entities.*;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class CarService {

    private CarDAO carDAO;

    public CarToBuyEntity findCarToBuy(String vin) {
        Optional<CarToBuyEntity> carToBuyByVin = carDAO.findCarToBuyByVin(vin);
        if (carToBuyByVin.isEmpty()) {
            throw new RuntimeException("Could not find car by vin: [%s]".formatted(vin));
        }
        return carToBuyByVin.get();
    }

    public Optional<CarToServiceEntity> findCarToService(String vin) {
        return carDAO.findCarToServiceByVin(vin);
    }

    public void saveCarToService (CarToServiceEntity car){
        carDAO.saveCarToService(car);
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
        CarHistory carHistory = carDAO.findHistoryByVin(vin);
        log.info("CAR HISTORY ( [{}] ): ", vin);
        carHistory.getServiceRequests().forEach(this::printRequests);
    }

    private void printRequests(CarHistory.ServiceRequest serviceRequest) {
        log.info("SERVICE REQUEST ( [{}] ):", serviceRequest);
        serviceRequest.services().forEach(service -> log.info("SERVICE ( [{}] ):", service));
        serviceRequest.parts().forEach(part -> log.info("PART ( [{}] ):", part));
    }

    public void showCarHistory2(String vin) {
        carDAO.findHistoryByVin2(vin);
    }

}
