package pl.zajavka;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.zajavka.business.DAO.CarToBuyDAO;
import pl.zajavka.business.managment.*;
import pl.zajavka.infrastructure.configuration.ApplicationConfig;
import pl.zajavka.infrastructure.repository.CarToBuyRepository;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//        CarDealershipManagement carDealershipManagement = context.getBean(CarDealershipManagement.class);
        CarPurchaseService carPurchaseService = context.getBean(CarPurchaseService.class);
        CarServiceRequestService carServiceRequestService = context.getBean(CarServiceRequestService.class);
        CarServiceManagementService carServiceManagementService = context.getBean(CarServiceManagementService.class);
        CarService carService = context.getBean(CarService.class);
//
//        carDealershipManagement.clear();
//
//        carDealershipManagement.saveAll();

        carPurchaseService.createPurchase();

        carServiceRequestService.createServiceCarRequests();

        carServiceManagementService.processService();

        carService.showCarHistory("2C3CDYAG2DH731952");
        carService.showCarHistory("1GCEC19X27Z109567");
//        carService.showCarHistory2("2C3CDYAG2DH731952");
//        carService.showCarHistory2("1GCEC19X27Z109567");


    }
}
