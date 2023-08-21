package pl.zajavka.model.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zajavka.DAO.MechanicDAO;
import pl.zajavka.model.configuration.HibernateUtil;
import pl.zajavka.model.entities.CustomerEntity;
import pl.zajavka.model.entities.MechanicEntity;

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
