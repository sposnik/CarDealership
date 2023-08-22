package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.CarToServiceDAO;
import pl.zajavka.infrastructure.entities.*;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarToServiceJpaRepository;
import pl.zajavka.model.CarHistory;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Repository
public class CarToServiceRepository implements CarToServiceDAO {

    private CarToServiceJpaRepository carToServiceJpaRepository;


    @Override
    public Optional<CarToServiceEntity> findCarToServiceByVin(String vin) {
        return carToServiceJpaRepository.findCarToServiceByVin(vin);
    }

    @Override
    public void saveCarToService(CarToServiceEntity car) {
        carToServiceJpaRepository.saveAndFlush(car);
    }

    @Override
    public CarHistory findHistoryByVin(String vin) {

        Optional<CarToServiceEntity> car
                = carToServiceJpaRepository.findCarToServiceHistoryByVin(vin);

        if (car.isEmpty()) {
            throw new RuntimeException("No such car in database with vin: " + vin);
        }
        CarHistory result = CarHistory.builder()
                .carVin(car.get().getVin())
                .serviceRequests(car.get().getCarServiceRequests().stream().map(this::mapServiceRequest).toList())
                .build();

        return result;
    }


    private CarHistory.ServiceRequest mapServiceRequest(CarServiceRequestEntity entity) {
        return CarHistory.ServiceRequest.builder()
                .serviceRequestNumber(entity.getCarServiceRequestNumber())
                .receivedDateTime(entity.getReceivedDateTime())
                .completedDateTime(entity.getCompletedDateTime())
                .customerComment(entity.getCustomerComment())
                .services(mapServices(entity.getServiceMechanics().stream().map(ServiceMechanicEntity::getService).toList()))
                .parts(mapParts(entity.getServiceParts().stream().map(ServicePartEntity::getPart).toList()))
                .build();
    }

    private List<CarHistory.Service> mapServices(List<ServiceCatalogEntity> entities) {
        return entities.stream()
                .map(service -> CarHistory.Service.builder()
                        .serviceCode(service.getServiceCode())
                        .description(service.getDescription())
                        .price(service.getPrice())
                        .build())
                .toList();
    }

    private List<CarHistory.Part> mapParts(List<PartEntity> entities) {
        return entities.stream()
                .map(part -> CarHistory.Part.builder()
                        .serialNumber(part.getSerialNumber())
                        .description(part.getDescription())
                        .price(part.getPrice())
                        .build())
                .toList();
    }


}
