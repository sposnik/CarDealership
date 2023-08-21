package pl.zajavka.model.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import pl.zajavka.DAO.CarDAO;
import pl.zajavka.model.configuration.HibernateUtil;
import pl.zajavka.model.entities.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class CarRepository implements CarDAO {

    @Override
    public Optional<CarToBuyEntity> findCarToBuyByVin(String vin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT ca FROM CarToBuyEntity ca WHERE ca.vin = :vin";
            Optional<CarToBuyEntity> result = session.createQuery(query, CarToBuyEntity.class)
                    .setParameter("vin", vin)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Optional<CarToServiceEntity> findCarToServiceByVin(String vin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT ca FROM CarToServiceEntity ca WHERE ca.vin = :vin";
            Optional<CarToServiceEntity> result = session.createQuery(query, CarToServiceEntity.class)
                    .setParameter("vin", vin)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void saveCarToService(CarToServiceEntity car) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            if (Objects.isNull(car.getCarToServiceId())) {
                session.persist(car);
            } else {
                session.merge(car);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public CarHistory findHistoryByVin(String vin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT ca FROM CarToServiceEntity ca WHERE ca.vin = :vin";
            Optional<CarToServiceEntity> car = session.createQuery(query, CarToServiceEntity.class)
                    .setParameter("vin", vin)
                    .uniqueResultOptional();

            if (car.isEmpty()) {
                throw new RuntimeException("No such car in database with vin: " + vin);
            }
            CarHistory result = CarHistory.builder()
                    .carVin(car.get().getVin())
                    .serviceRequests(car.get().getCarServiceRequests().stream().map(this::mapServiceRequest).toList())
                    .build();

            session.getTransaction().commit();
            return result;

        }
    }

    @Override
    public void findHistoryByVin2(String vin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT ca FROM CarToServiceEntity ca WHERE ca.vin = :vin";
            Optional<CarToServiceEntity> car = session.createQuery(query, CarToServiceEntity.class)
                    .setParameter("vin", vin)
                    .uniqueResultOptional();

            if (car.isEmpty()) {
                throw new RuntimeException("No such car in database with vin: " + vin);
            }
            CarHistory2 result = CarHistory2.builder()
                    .carVin(vin)
                    .serviceRequests(car.get().getCarServiceRequests())
                    .build();
            printHistory(result);
            session.getTransaction().commit();
        }
    }

    private CarHistory.ServiceRequest mapServiceRequest(CarServiceRequestEntity entity) {
        return CarHistory.ServiceRequest.builder()
                .serviceRequestNumber(entity.getCarServiceRequestNumber())
                .receivedDateTime(entity.getReceivedDateTime())
                .completedDateTime(entity.getCompletedDateTime())
                .customerComment(entity.getCustomerComment())
                .services(mapServices(entity.getServiceMechanics().stream().map(ServiceMechanicEntity::getService).toList()))
                .parts(mapParts(entity.getServiceParts().stream().map(ServicePartEntity::getPart).toList()))
                .build();
    }

    private List<CarHistory.Service> mapServices(List<ServiceEntity> entities) {
        return entities.stream()
                .map(service -> CarHistory.Service.builder()
                        .serviceCode(service.getServiceCode())
                        .description(service.getDescription())
                        .price(service.getPrice())
                        .build())
                .toList();
    }

    private List<CarHistory.Part> mapParts(List<PartEntity> entities) {
        return entities.stream()
                .map(part -> CarHistory.Part.builder()
                        .serialNumber(part.getSerialNumber())
                        .description(part.getDescription())
                        .price(part.getPrice())
                        .build())
                .toList();
    }

    public void printHistory(CarHistory2 carHistory) {
        log.info("#######CAR HISTORY ( [{}] ): ", carHistory.getCarVin());
        carHistory.getServiceRequests().forEach(this::printRequests2);
    }
    private void printRequests2(CarServiceRequestEntity serviceRequest) {
        log.info("########SERVICE REQUEST ( [{}] ):", serviceRequest);
        serviceRequest.getServiceMechanics().forEach(service -> log.info("#########SERVICE ( [{}] ):", service));
        serviceRequest.getServiceParts().forEach(part -> log.info("#########PART ( [{}] ):", part));
    }
}
