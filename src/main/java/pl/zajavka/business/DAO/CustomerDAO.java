package pl.zajavka.business.DAO;

import pl.zajavka.model.Customer;

import java.util.Optional;

public interface CustomerDAO {

    Customer saveCustomer(Customer customer);

    Optional<Customer> findByEmail(String email);

    void issueInvoice(Customer customer);
}
