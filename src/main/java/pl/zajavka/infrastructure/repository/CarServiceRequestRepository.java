package pl.zajavka.infrastructure.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.CarServiceRequestDAO;
import pl.zajavka.infrastructure.configuration.HibernateUtil;
import pl.zajavka.infrastructure.entities.CarServiceRequestEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarServiceRequestJpaRepository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class CarServiceRequestRepository implements CarServiceRequestDAO {

    private CarServiceRequestJpaRepository carServiceRequestJpaRepository;



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

