package pl.zajavka.business.managment;

import org.springframework.stereotype.Service;
import pl.zajavka.infrastructure.entities.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class DataPrepareService {

    public static List<List<?>> listsToPersist() throws IOException {
        List<SalesmanEntity> salesmen = InputManagement.listOfData(
                InputManagement.getInitialInputData(), Keys.Entity.SALESMAN, InputMapper::salesmanMapper);
        List<MechanicEntity> mechanics = InputManagement.listOfData(
                InputManagement.getInitialInputData(), Keys.Entity.MECHANIC, InputMapper::mechanicMapper);
        List<PartEntity> parts = InputManagement.listOfData(
                InputManagement.getInitialInputData(), Keys.Entity.PART, InputMapper::partMapper);
        List<CarToBuyEntity> carsToBuy = InputManagement.listOfData(
                InputManagement.getInitialInputData(), Keys.Entity.CAR, InputMapper::carToBuyMapper);
        List<ServiceEntity> services = InputManagement.listOfData(
                InputManagement.getInitialInputData(), Keys.Entity.SERVICE, InputMapper::serviceMapper);
        return List.of(salesmen, mechanics, carsToBuy, parts, services);
    }

    public static List<Map<String, List<String>>> listOfFirstBuy() {
        try {
            return InputManagement.listOfData(
                    InputManagement.getSimulationInputData(),
                    Keys.Simulation.BUY_FIRST_TIME,
                    InputMapper::simulationDataMapper
            );
        } catch (IOException e) {
            throw new RuntimeException("Problem occurred: " + e.getMessage());
        }
    }

    public static List<Map<String, List<String>>> listOfFollowingBuy() {
        try {
            return InputManagement.listOfData(
                    InputManagement.getSimulationInputData(),
                    Keys.Simulation.BUY_AGAIN,
                    InputMapper::simulationDataMapper
            );
        } catch (IOException e) {
            throw new RuntimeException("Problem occurred: " + e.getMessage());
        }
    }

    public static List<Map<String, List<String>>> mapOfRequests() {
        try {
            return InputManagement.listOfData(
                    InputManagement.getSimulationInputData(),
                    Keys.Simulation.SERVICE_REQUEST,
                    InputMapper::simulationDataMapper
            );
        } catch (IOException e) {
            throw new RuntimeException("Problem occurred: " + e.getMessage());
        }
    }

    public CustomerEntity buildCustomer(List<String> strings, InvoiceEntity invoice) {
        if (strings.size() == 1) {
            return CustomerEntity.builder()
                    .email(strings.get(0))
                    .build();
        }
        CustomerEntity customer = CustomerEntity.builder()
                .name(strings.get(0))
                .surname(strings.get(1))
                .phone(strings.get(2))
                .email(strings.get(3))
                .address(AddressEntity.builder()
                        .country(strings.get(4))
                        .city(strings.get(5))
                        .postalCode(strings.get(6))
                        .address(strings.get(7))
                        .build())
                .build();
        if (!Objects.isNull(invoice)) {
            customer.setInvoices(Set.of(invoice));
        }
        return customer;
    }

    public CarToServiceEntity buildCarToService(List<String> strings) {
        if (strings.size() == 1) {
            return CarToServiceEntity.builder()
                    .vin(strings.get(0))
                    .build();
        }
        return CarToServiceEntity.builder()
                .vin(strings.get(0))
                .brand(strings.get(1))
                .model(strings.get(2))
                .year(Integer.parseInt(strings.get(3)))
                .build();
    }

    public List<Map<String, List<String>>> mapOfProcessingService() {
        try {
            return InputManagement.listOfData(
                    InputManagement.getSimulationInputData(),
                    Keys.Simulation.DO_THE_SERVICE,
                    InputMapper::simulationDataMapper
            );
        } catch (IOException e) {
            throw new RuntimeException("Problem occurred: " + e.getMessage());
        }
    }
}
