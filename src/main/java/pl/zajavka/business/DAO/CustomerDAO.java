package pl.zajavka.business.DAO;

import pl.zajavka.infrastructure.entities.CustomerEntity;

import java.util.Optional;

public interface CustomerDAO {


    void saveCustomer(CustomerEntity customer);

    Optional<CustomerEntity> findByEmail(String email);

    void issueInvoice(CustomerEntity customer);
}
