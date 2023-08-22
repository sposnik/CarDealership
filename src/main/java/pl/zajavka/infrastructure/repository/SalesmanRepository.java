package pl.zajavka.infrastructure.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.SalesmanDAO;
import pl.zajavka.infrastructure.configuration.HibernateUtil;
import pl.zajavka.infrastructure.entities.SalesmanEntity;

import java.util.Objects;
import java.util.Optional;

@Repository
public class SalesmanRepository implements SalesmanDAO {
    @Override
    public Optional<SalesmanEntity> findByPesel(String pesel) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT se FROM SalesmanEntity se WHERE se.pesel = :pesel";
            Optional<SalesmanEntity> result = session.createQuery(query, SalesmanEntity.class)
                    .setParameter("pesel", pesel)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }
}
