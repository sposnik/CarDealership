package pl.zajavka.business.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.DAO.CarToBuyDAO;
import pl.zajavka.business.DAO.CarToServiceDAO;
import pl.zajavka.model.CarHistory;
import pl.zajavka.model.CarToBuy;
import pl.zajavka.model.CarToService;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CarService {

    private CarToServiceDAO carToServiceDAO;

    private CarToBuyDAO carToBuyDAO;

    public CarToBuy findCarToBuy(String vin) {
        Optional<CarToBuy> carToBuyByVin = carToBuyDAO.findCarToBuyByVin(vin);
        if (carToBuyByVin.isEmpty()) {
            throw new RuntimeException("Could not find car by vin: [%s]".formatted(vin));
        }
        return carToBuyByVin.get();
    }

    public Optional<CarToService> findCarToService(String vin) {
        return carToServiceDAO.findCarToServiceByVin(vin);
    }

    public CarToService saveCarToService(CarToService car) {
        return carToServiceDAO.saveCarToService(car);
    }

    @Transactional
    public CarToService copyCarFromBuyToService(CarToService carToService) {
        CarToBuy carToBuy = findCarToBuy(carToService.getVin());
        CarToService newCarToService = CarToService.builder()
                .vin(carToBuy.getVin())
                .brand(carToBuy.getBrand())
                .model(carToBuy.getModel())
                .year(carToBuy.getYear())
                .build();
        return carToServiceDAO.saveCarToService(newCarToService);
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
