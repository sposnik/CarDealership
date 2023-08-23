package pl.zajavka.business.DAO;

import pl.zajavka.model.CarToBuy;

import java.util.Optional;


public interface CarToBuyDAO {

    Optional<CarToBuy> findCarToBuyByVin(String vin);

}
