package pl.zajavka.DAO;

import pl.zajavka.model.entities.CarToServiceEntity;
import pl.zajavka.model.entities.CustomerEntity;

import java.util.Optional;

public interface CustomerDAO {

    void saveCustomer(CustomerEntity customer);

    Optional<CustomerEntity> findByEmail(String email);

    void issueInvoice(CustomerEntity customer);
}
