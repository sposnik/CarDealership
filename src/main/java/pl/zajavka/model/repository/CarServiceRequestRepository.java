package pl.zajavka.model.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zajavka.DAO.CarServiceRequestDAO;
import pl.zajavka.model.configuration.HibernateUtil;
import pl.zajavka.model.entities.CarServiceRequestEntity;
import pl.zajavka.model.entities.CustomerEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
public class CarServiceRequestRepository implements CarServiceRequestDAO {



    @Override
    public void createServiceRequest(CarServiceRequestEntity request) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            session.persist(request);

            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<CarServiceRequestEntity> findRequestByVin(String carVin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = """
                    SELECT rq FROM CarServiceRequestEntity rq WHERE rq.car.vin = :vin
                    AND rq.completedDateTime is NULL
                    """;
            Optional<CarServiceRequestEntity> result = session.createQuery(query, CarServiceRequestEntity.class)
                    .setParameter("vin", carVin)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }
}

