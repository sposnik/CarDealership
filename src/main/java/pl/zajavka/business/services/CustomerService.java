package pl.zajavka.business.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.CustomerDAO;
import pl.zajavka.model.Customer;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerDAO customerDAO;

    public void issueInvoice(Customer customer) {
        customerDAO.issueInvoice(customer);
    }

    public Customer findCustomer(String email) {
        Optional<Customer> customer = customerDAO.findByEmail(email);
        if (customer.isEmpty()) {
            throw new RuntimeException("Could not find customer by email: [%s]".formatted(email));
        }
        return customer.get();
    }

    public Customer saveCustomer(Customer customer) {
        return customerDAO.saveCustomer(customer);
    }
}
