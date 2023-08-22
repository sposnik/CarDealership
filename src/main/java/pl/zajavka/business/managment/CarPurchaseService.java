package pl.zajavka.business.managment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.infrastructure.entities.CarToBuyEntity;
import pl.zajavka.infrastructure.entities.CustomerEntity;
import pl.zajavka.infrastructure.entities.InvoiceEntity;
import pl.zajavka.infrastructure.entities.SalesmanEntity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
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