package pl.zajavka.managment;

import lombok.AllArgsConstructor;
import pl.zajavka.model.entities.CarToBuyEntity;
import pl.zajavka.model.entities.CustomerEntity;
import pl.zajavka.model.entities.InvoiceEntity;
import pl.zajavka.model.entities.SalesmanEntity;
import pl.zajavka.model.repository.CarServiceRequestRepository;
import pl.zajavka.model.repository.CustomerRepository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class CarPurchaseService {

    private CustomerService customerService;

    private CarService carService;

    private SalesmanService salesmanService;

    private DataPrepareService dataPrepareService;

    public void createPurchase() {
        var listOfFirstBuy = DataPrepareService.listOfFirstBuy();
        var listOfFollowingBuy = DataPrepareService.listOfFollowingBuy();

        List<CustomerEntity> listFirstTimeCustomers = listOfFirstBuy.stream()
                .map(this::firstTimeCustomerMapper)
                .toList();
        listFirstTimeCustomers.forEach(customerService::issueInvoice);

        List<CustomerEntity> listFollowingCustomers = listOfFollowingBuy.stream()
                .map(this::followingBuyCustomerMapper)
                .toList();
        listFollowingCustomers.forEach(customerService::issueInvoice);

    }

    private CustomerEntity firstTimeCustomerMapper(Map<String, List<String>> stringListMap) {
        SalesmanEntity salesman
                = salesmanService.findSalesman(stringListMap.get(Keys.Entity.SALESMAN.toString()).get(0));
        CarToBuyEntity carToBuy
                = carService.findCarToBuy(stringListMap.get(Keys.Entity.CAR.toString()).get(0));
        InvoiceEntity invoice = buildInvoice(carToBuy, salesman);

        return dataPrepareService.buildCustomer(stringListMap.get(Keys.Entity.CUSTOMER.toString()), invoice);

    }

    private CustomerEntity followingBuyCustomerMapper(Map<String, List<String>> stringListMap) {
        SalesmanEntity salesman
                = salesmanService.findSalesman(stringListMap.get(Keys.Entity.SALESMAN.toString()).get(0));
        CarToBuyEntity carToBuy
                = carService.findCarToBuy(stringListMap.get(Keys.Entity.CAR.toString()).get(0));
        CustomerEntity customer
                = customerService.findCustomer(stringListMap.get(Keys.Entity.CUSTOMER.toString()).get(0));

        InvoiceEntity invoice = buildInvoice(carToBuy, salesman);
        customer.getInvoices().add(invoice);

        return customer;
    }

    private InvoiceEntity buildInvoice(CarToBuyEntity carToBuy, SalesmanEntity salesman) {
        return InvoiceEntity.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .dateTime(OffsetDateTime.of(2023, 10, 1, 11, 0, 0, 0, ZoneOffset.UTC))
                .car(carToBuy)
                .salesman(salesman)
                .build();
    }

}
