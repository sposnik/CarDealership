package pl.zajavka.infrastructure.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.PartDAO;
import pl.zajavka.infrastructure.configuration.HibernateUtil;
import pl.zajavka.infrastructure.entities.PartEntity;

import java.util.Objects;
import java.util.Optional;

@Repository
public class PartRepository implements PartDAO {
    @Override
    public Optional<PartEntity> findBySerialNumber(String serialNumber) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT pa FROM PartEntity pa WHERE pa.serialNumber = :serialNumber";
            Optional<PartEntity> result = session.createQuery(query, PartEntity.class)
                    .setParameter("serialNumber", serialNumber)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }
}
