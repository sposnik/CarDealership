package pl.zajavka.infrastructure.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.ServiceDAO;
import pl.zajavka.infrastructure.configuration.HibernateUtil;
import pl.zajavka.infrastructure.entities.ServiceEntity;

import java.util.Objects;
import java.util.Optional;

@Repository
public class ServiceCatalogRepository implements ServiceDAO {
    @Override
    public Optional<ServiceEntity> findServiceByServiceCode(String serviceCode) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT sv FROM ServiceEntity sv WHERE sv.serviceCode = :serviceCode";
            Optional<ServiceEntity> result = session.createQuery(query, ServiceEntity.class)
                    .setParameter("serviceCode", serviceCode)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }
}
