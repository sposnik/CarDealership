package pl.zajavka.infrastructure.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.MechanicDAO;
import pl.zajavka.infrastructure.configuration.HibernateUtil;
import pl.zajavka.infrastructure.entities.MechanicEntity;

import java.util.Objects;
import java.util.Optional;


@Repository
public class MechanicRepository implements MechanicDAO {
    @Override
    public Optional<MechanicEntity> findByPesel(String pesel) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT mc FROM MechanicEntity mc WHERE mc.pesel = :pesel";
            Optional<MechanicEntity> result = session.createQuery(query, MechanicEntity.class)
                    .setParameter("pesel", pesel)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }
}
