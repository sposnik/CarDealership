package pl.zajavka.business.DAO;

import org.springframework.stereotype.Component;
import pl.zajavka.infrastructure.entities.CarToBuyEntity;

import java.util.Optional;


public interface CarToBuyDAO {

    Optional<CarToBuyEntity> findCarToBuyByVin(String vin);

}
