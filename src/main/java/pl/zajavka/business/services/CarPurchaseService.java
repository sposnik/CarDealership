package pl.zajavka.business.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.services.utils.Keys;
import pl.zajavka.model.CarToBuy;
import pl.zajavka.model.Customer;
import pl.zajavka.model.Invoice;
import pl.zajavka.model.Salesman;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

        List<Customer> listFirstTimeCustomers = listOfFirstBuy.stream()
                .map(this::firstTimeCustomerMapper)
                .toList();
        listFirstTimeCustomers.forEach(customerService::issueInvoice);

        List<Customer> listFollowingCustomers = listOfFollowingBuy.stream()
                .map(this::followingBuyCustomerMapper)
                .toList();
        listFollowingCustomers.forEach(customerService::issueInvoice);

    }

    private Customer firstTimeCustomerMapper(Map<String, List<String>> stringListMap) {
        CarToBuy carToBuy
                = carService.findCarToBuy(stringListMap.get(Keys.Domains.CAR.toString()).get(0));
        Salesman salesman
                = salesmanService.findSalesman(stringListMap.get(Keys.Domains.SALESMAN.toString()).get(0));

        Invoice invoice = buildInvoice(carToBuy, salesman);

        return dataPrepareService.buildCustomer(stringListMap.get(Keys.Domains.CUSTOMER.toString()), invoice);
    }

    private Customer followingBuyCustomerMapper(Map<String, List<String>> stringListMap) {
        Salesman salesman
                = salesmanService.findSalesman(stringListMap.get(Keys.Domains.SALESMAN.toString()).get(0));
        CarToBuy carToBuy
                = carService.findCarToBuy(stringListMap.get(Keys.Domains.CAR.toString()).get(0));
        Customer customer
                = customerService.findCustomer(stringListMap.get(Keys.Domains.CUSTOMER.toString()).get(0));

        Invoice invoice = buildInvoice(carToBuy, salesman);
        Set<Invoice> invoices = customer.getInvoices();
        invoices.add(invoice);
        customer.withInvoices(invoices);

        return customer;
    }

    private Invoice buildInvoice(CarToBuy carToBuy, Salesman salesman) {
        return Invoice.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .dateTime(OffsetDateTime.of(2023, 10, 1, 11, 0, 0, 0, ZoneOffset.UTC))
                .car(carToBuy)
                .salesman(salesman)
                .build();
    }

}
