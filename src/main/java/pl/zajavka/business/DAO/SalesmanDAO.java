package pl.zajavka.business.DAO;

import pl.zajavka.infrastructure.entities.SalesmanEntity;

import java.util.Optional;

public interface SalesmanDAO {

    Optional<SalesmanEntity> findByPesel (String pesel);

}
