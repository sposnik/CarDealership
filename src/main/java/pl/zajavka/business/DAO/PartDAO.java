package pl.zajavka.business.DAO;

import pl.zajavka.model.Part;

import java.util.Optional;

public interface PartDAO {

    Optional<Part> findBySerialNumber(String serialNumber);

}
