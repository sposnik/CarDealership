package pl.zajavka.business.services;

import org.springframework.stereotype.Service;
import pl.zajavka.business.services.utils.InputManagement;
import pl.zajavka.business.services.utils.InputMapper;
import pl.zajavka.business.services.utils.Keys;
import pl.zajavka.model.Address;
import pl.zajavka.model.CarToService;
import pl.zajavka.model.Customer;
import pl.zajavka.model.Invoice;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class DataPrepareService {

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

    public Customer buildCustomer(List<String> strings, Invoice invoice) {
        if (strings.size() == 1) {
            return Customer.builder()
                    .email(strings.get(0))
                    .build();
        }
        Customer customer = Customer.builder()
                .name(strings.get(0))
                .surname(strings.get(1))
                .phone(strings.get(2))
                .email(strings.get(3))
                .address(Address.builder()
                        .country(strings.get(4))
                        .city(strings.get(5))
                        .postalCode(strings.get(6))
                        .address(strings.get(7))
                        .build())
                .build();
        if (!Objects.isNull(invoice)) {
            Set<Invoice> actualInvoices = customer.getInvoices();
            actualInvoices.add(invoice);
            customer = customer.withInvoices(actualInvoices);
        }
        return customer;
    }

    public CarToService buildCarToService(List<String> strings) {
        if (strings.size() == 1) {
            return CarToService.builder()
                    .vin(strings.get(0))
                    .build();
        }
        return CarToService.builder()
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
