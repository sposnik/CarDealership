package pl.zajavka.business.DAO;

import pl.zajavka.model.Salesman;

import java.util.Optional;

public interface SalesmanDAO {

    Optional<Salesman> findByPesel (String pesel);

}
