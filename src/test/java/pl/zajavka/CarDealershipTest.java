package pl.zajavka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.zajavka.business.managment.*;
import pl.zajavka.infrastructure.configuration.ApplicationConfig;
import pl.zajavka.infrastructure.entities.CarToBuyEntity;


@Slf4j
@Testcontainers
@AllArgsConstructor(onConstructor = @__(@Autowired))
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringJUnitConfig(classes = {ApplicationConfig.class})
public class CarDealershipTest {

    @Container
    static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>("postgres:15.0");

    private CarDealershipManagement carDealershipManagement;
    private CarPurchaseService carPurchaseService;
    private CarServiceRequestService carServiceRequestService;
    private CarServiceManagementService carServiceManagementService;
    private CarService carService;


    @DynamicPropertySource
    static void postgreSQLProperties(DynamicPropertyRegistry registry) {
        registry.add("jdbc.url", postgreSQL::getJdbcUrl);
        registry.add("jdbc.user", postgreSQL::getUsername);
        registry.add("jdbc.pass", postgreSQL::getPassword);
    }

    @Test
    @Order(1)
    void clear() {
        log.info("##### TEST CLEAR");
//        carDealershipManagement.clear();
    }

    @Test
    @Order(2)
    void saveData() {
        log.info("##### TEST SAVE ALL");
//        carDealershipManagement.saveAll();
    }

    @Test
    @Order(3)
    void purchase() {
        log.info("##### CREATE PURCHASE");
        carPurchaseService.createPurchase();
    }

    @Test
    @Order(4)
    void request() {
        log.info("##### CREATE CAR REQUEST");
        carServiceRequestService.createServiceCarRequests();
    }

    @Test
    @Order(5)
    void process() {
        log.info("##### PROCESS REQUEST");
        carServiceManagementService.processService();
    }

    @Test
    @Order(6)
    void history() {
        log.info("##### CREATE CAR HISTORY");
        carService.showCarHistory("2C3CDYAG2DH731952");
        carService.showCarHistory("1GCEC19X27Z109567");
    }

    @Test
    @Order(7)
    void verify() {
        log.info("##### VERIFY");
        CarToBuyEntity car = carService.findCarToBuy("1FT7X2B60FEA74019");
        Assertions.assertEquals("Series 1", car.getModel());
        Assertions.assertEquals("BMW", car.getBrand());
    }






}
