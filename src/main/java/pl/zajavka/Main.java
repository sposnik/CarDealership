package pl.zajavka;

import lombok.SneakyThrows;
import pl.zajavka.DAO.*;
import pl.zajavka.managment.*;
import pl.zajavka.model.repository.*;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        DataPrepareService dataPrepareService = new DataPrepareService();
        CarDealershipManagementDAO carDealershipManagementRepository = new CarDealershipManagementRepository();
        CarDealershipManagement carDealershipManagement = new CarDealershipManagement(
                carDealershipManagementRepository,
                dataPrepareService);
        carDealershipManagement.clear();

        CustomerDAO customerDAO = new CustomerRepository();
        SalesmanDAO salesmanDAO = new SalesmanRepository();
        CarDAO carDAO = new CarRepository();
        CarServiceRequestDAO carServiceRequestDAO = new CarServiceRequestRepository();

        CustomerService customerService = new CustomerService(customerDAO);
        CarService carService = new CarService(carDAO);
        SalesmanService salesmanService = new SalesmanService(salesmanDAO);


        carDealershipManagement.saveAll();

        CarPurchaseService carPurchaseService = new CarPurchaseService(
                customerService,
                carService,
                salesmanService,
                dataPrepareService);

        carPurchaseService.createPurchase();

        CarServiceRequestService carServiceRequestService = new CarServiceRequestService
                (customerService, carService, carServiceRequestDAO, dataPrepareService);

        carServiceRequestService.createServiceCarRequests();

        CarServiceManagementDAO carServiceManagementRepository = new CarServiceManagementRepository();
        MechanicDAO mechanicDAO = new MechanicRepository();
        PartDAO partDAO = new PartRepository();
        MechanicService mechanicService = new MechanicService(mechanicDAO);
        PartService partService = new PartService(partDAO);
        ServiceDAO serviceDAO = new ServiceCatalogRepository();
        ServiceCatalogService serviceCatalogService = new ServiceCatalogService(serviceDAO);

        CarServiceManagementService carServiceManagementService
                = new CarServiceManagementService(
                        dataPrepareService,
                carServiceManagementRepository,
                carService,
                carServiceRequestService,
                mechanicService,
                partService,
                serviceCatalogService);

        carServiceManagementService.processService();

        carService.showCarHistory("2C3CDYAG2DH731952");
        carService.showCarHistory("1GCEC19X27Z109567");
        carService.showCarHistory2("2C3CDYAG2DH731952");
        carService.showCarHistory2("1GCEC19X27Z109567");


    }
}
