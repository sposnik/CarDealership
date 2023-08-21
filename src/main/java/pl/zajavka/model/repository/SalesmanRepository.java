package pl.zajavka.model.repository;

import org.hibernate.Session;
import pl.zajavka.DAO.SalesmanDAO;
import pl.zajavka.model.configuration.HibernateUtil;
import pl.zajavka.model.entities.SalesmanEntity;

import java.util.Objects;
import java.util.Optional;

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
