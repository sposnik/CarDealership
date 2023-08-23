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
import pl.zajavka.business.services.CarPurchaseService;
import pl.zajavka.business.services.CarService;
import pl.zajavka.business.services.CarServiceManagementService;
import pl.zajavka.business.services.CarServiceRequestService;
import pl.zajavka.infrastructure.configuration.ApplicationConfig;
import pl.zajavka.infrastructure.entities.CarServiceRequestEntity;
import pl.zajavka.infrastructure.entities.ServiceMechanicEntity;
import pl.zajavka.infrastructure.entities.ServicePartEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarServiceRequestJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.InvoiceJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.ServiceMechanicJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.ServicePartJpaRepository;
import pl.zajavka.model.CarToBuy;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@Testcontainers
@AllArgsConstructor(onConstructor = @__(@Autowired))
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringJUnitConfig(classes = {ApplicationConfig.class})
public class CarDealershipTest {

    @Container
    static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>("postgres:15.0");
    private final InvoiceJpaRepository invoiceJpaRepository;
    private final CarServiceRequestJpaRepository carServiceRequestJpaRepository;
    private final ServiceMechanicJpaRepository serviceMechanicJpaRepository;
    private final ServicePartJpaRepository servicePartJpaRepository;
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
    void purchase() {
        log.info("##### CREATE PURCHASE");
        carPurchaseService.createPurchase();
    }

    @Test
    @Order(2)
    void request() {
        log.info("##### CREATE CAR REQUEST");
        carServiceRequestService.createServiceCarRequests();
    }

    @Test
    @Order(3)
    void process() {
        log.info("##### PROCESS REQUEST");
        carServiceManagementService.processService();
    }

    @Test
    @Order(4)
    void history() {
        log.info("##### CREATE CAR HISTORY");
        carService.showCarHistory("2C3CDYAG2DH731952");
        carService.showCarHistory("1GCEC19X27Z109567");
    }

    @Test
    @Order(5)
    void verify() {
        log.info("##### VERIFY");
        CarToBuy car = carService.findCarToBuy("1FT7X2B60FEA74019");
        Assertions.assertEquals("Series 1", car.getModel());
        Assertions.assertEquals("BMW", car.getBrand());
        invoiceJpaRepository.findAll();
        assertEquals(6, invoiceJpaRepository.findAll().size());

        List<CarServiceRequestEntity> allServiceRequests = carServiceRequestJpaRepository.findAll();
        assertEquals(3, allServiceRequests.size());
        assertEquals(2, allServiceRequests.stream().filter(sr -> Objects.nonNull(sr.getCompletedDateTime())).count());

        List<ServiceMechanicEntity> allServiceMechanics = serviceMechanicJpaRepository.findAll();
        assertEquals(5, allServiceMechanics.size());

        List<ServicePartEntity> allServiceParts = servicePartJpaRepository.findAll();
        assertEquals(4, allServiceParts.size());
    }

}
