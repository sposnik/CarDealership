package pl.zajavka.business.managment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.CarServiceRequestDAO;
import pl.zajavka.infrastructure.entities.CarServiceRequestEntity;
import pl.zajavka.infrastructure.entities.CarToServiceEntity;
import pl.zajavka.infrastructure.entities.CustomerEntity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
@AllArgsConstructor
public class CarServiceRequestService {

    private CustomerService customerService;

    private CarService carService;

    private CarServiceRequestDAO carServiceRequestDAO;
    private DataPrepareService dataPrepareService;



    public void createServiceCarRequests() {

        var listOfRequests = DataPrepareService.mapOfRequests().stream()
                .map(this::requestToListMapper)
                .map(this::prepareServiceRequest)
                .toList();
        listOfRequests.stream().
                forEach(this::saveRequest);

    }

    private void saveRequest(CarServiceRequestEntity request) {
        carService.saveCarToService(request.getCar());
        customerService.saveCustomer(request.getCustomer());
        carServiceRequestDAO.createServiceRequest(request);
    }

    private CarServiceRequestEntity prepareServiceRequest(CarServiceRequestEntity request) {
        CarToServiceEntity carToService = request.getCar();
        CustomerEntity customer = request.getCustomer();
        if (Objects.isNull(carToService.getBrand()) && Objects.isNull(carToService.getModel()) && Objects.isNull(carToService.getYear())) {
            carToService = carService.findCarToService(carToService.getVin())
                    .orElse(carService.copyCarFromBuyToService(carToService));
            customer = customerService.findCustomer(customer.getEmail());
        }
        Random random = new Random();
        OffsetDateTime when = OffsetDateTime.of(2027, 1, 10, 10, 2, 10, 0, ZoneOffset.UTC);
        request.setCarServiceRequestNumber((String.valueOf(random.nextInt(100)).concat(String.valueOf(when.getYear()))));
        request.setReceivedDateTime(when);
        request.setCustomer(customer);
        request.setCar(carToService);
        customer.addServiceRequest(request);
        carToService.addServiceRequest(request);

        return request;
    }

    private CarServiceRequestEntity requestToListMapper(Map<String, List<String>> stringListMap) {

        CustomerEntity customer
                = dataPrepareService.buildCustomer(stringListMap.get(Keys.Entity.CUSTOMER.toString()), null);
        String comment = stringListMap.get(Keys.Properties.WHAT.toString()).get(0);
        CarToServiceEntity car = dataPrepareService.buildCarToService(stringListMap.get(Keys.Entity.CAR.toString()));

        return CarServiceRequestEntity.builder()
                .customer(customer)
                .car(car)
                .customerComment(comment)
                .build();

    }

    private CarServiceRequestEntity buildCarServiceRequestEntity(
            CarServiceRequestEntity request,
            CarToServiceEntity car,
            CustomerEntity customer
    ) {
        Random random = new Random();
        OffsetDateTime when = OffsetDateTime.of(2027, 1, 10, 10, 2, 10, 0, ZoneOffset.UTC);
        return CarServiceRequestEntity.builder()
                .carServiceRequestNumber((String.valueOf(random.nextInt(100)).concat(String.valueOf(when.getYear()))))
                .receivedDateTime(when)
                .customerComment(request.getCustomerComment())
                .customer(customer)
                .car(car)
                .build();
    }

    public CarServiceRequestEntity findActiveRequest(String carVin) {
        Optional<CarServiceRequestEntity> request = carServiceRequestDAO.findRequestByVin(carVin);
        if (request.isEmpty()) {
            throw new RuntimeException("Could not find request by email: [%s]".formatted(carVin));
        }
        return request.get();
    }
}
