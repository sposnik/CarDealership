package pl.zajavka.DAO;

import pl.zajavka.model.entities.SalesmanEntity;

import java.util.Optional;

public interface SalesmanDAO {

    Optional<SalesmanEntity> findByPesel (String pesel);

}
