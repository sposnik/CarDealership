package pl.zajavka.model.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zajavka.DAO.CarServiceManagementDAO;
import pl.zajavka.model.configuration.HibernateUtil;
import pl.zajavka.model.entities.CarServiceRequestEntity;
import pl.zajavka.model.entities.ServiceMechanicEntity;
import pl.zajavka.model.entities.ServicePartEntity;

import java.util.Objects;

@Repository
public class CarServiceManagementRepository implements CarServiceManagementDAO {


    @Override
    public void manage(CarServiceRequestEntity carServiceRequest, ServiceMechanicEntity serviceMechanicEntity) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(serviceMechanicEntity);
            if (!Objects.isNull(carServiceRequest.getCompletedDateTime())) {
                session.merge(carServiceRequest);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void manage(CarServiceRequestEntity carServiceRequest,
                       ServiceMechanicEntity serviceMechanicEntity,
                       ServicePartEntity servicePartEntity) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(serviceMechanicEntity);
            session.persist(servicePartEntity);
            if (!Objects.isNull(carServiceRequest.getCompletedDateTime())) {
                session.merge(carServiceRequest);
            }
            session.getTransaction().commit();
        }
    }
}
