package pl.zajavka.business.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.CarServiceRequestDAO;
import pl.zajavka.business.services.utils.Keys;
import pl.zajavka.model.CarServiceRequest;
import pl.zajavka.model.CarToService;
import pl.zajavka.model.Customer;

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

    private void saveRequest(CarServiceRequest request) {
       // carService.saveCarToService(request.getCar());
//        customerService.saveCustomer(request.getCustomer());
        carServiceRequestDAO.createServiceRequest(request);
    }

    private CarServiceRequest prepareServiceRequest(CarServiceRequest request) {
        CarToService carToService = request.getCar();
        Customer customer = request.getCustomer();
        if (carToService.carBoughtHere()) {
            carToService = carService.findCarToService(carToService.getVin())
                    .orElse(carService.copyCarFromBuyToService(carToService));
            customer = customerService.findCustomer(customer.getEmail());
        }else{
            carToService = carService.saveCarToService(carToService);
        }
        customer = customerService.saveCustomer(customer);
        Random random = new Random();
        OffsetDateTime when =
                OffsetDateTime.of(2027, 1, 10, 10, 2, 10, 0, ZoneOffset.UTC);
        request = request.withCarServiceRequestNumber(
                        (String.valueOf(random.nextInt(100)).concat(String.valueOf(when.getYear()))))
                .withReceivedDateTime(when)
                .withCustomer(customer)
                .withCar(carToService);
        Set<CarServiceRequest> actualRequests = customer.getCarServiceRequests();
        actualRequests.add(request);
        customer.withCarServiceRequests(actualRequests);
        carToService.withCarServiceRequests(actualRequests);

        return request;
    }

    private CarServiceRequest requestToListMapper(Map<String, List<String>> stringListMap) {

        Customer customer
                = dataPrepareService.buildCustomer(stringListMap.get(Keys.Domains.CUSTOMER.toString()), null);
        String comment = stringListMap.get(Keys.Properties.WHAT.toString()).get(0);
        CarToService car = dataPrepareService.buildCarToService(stringListMap.get(Keys.Domains.CAR.toString()));

        return CarServiceRequest.builder()
                .customer(customer)
                .car(car)
                .customerComment(comment)
                .build();

    }

    private CarServiceRequest buildCarServiceRequest(
            CarServiceRequest request,
            CarToService car,
            Customer customer
    ) {
        Random random = new Random();
        OffsetDateTime when = OffsetDateTime.of(2027, 1, 10, 10, 2, 10, 0, ZoneOffset.UTC);
        return CarServiceRequest.builder()
                .carServiceRequestNumber((String.valueOf(random.nextInt(100)).concat(String.valueOf(when.getYear()))))
                .receivedDateTime(when)
                .customerComment(request.getCustomerComment())
                .customer(customer)
                .car(car)
                .build();
    }

    public CarServiceRequest findActiveRequest(String carVin) {
        Optional<CarServiceRequest> request = carServiceRequestDAO.findRequestByVin(carVin);
        if (request.isEmpty()) {
            throw new RuntimeException("Could not find request by email: [%s]".formatted(carVin));
        }
        return request.get();
    }
}
