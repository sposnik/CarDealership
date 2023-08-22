package pl.zajavka.business.managment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.CustomerDAO;
import pl.zajavka.infrastructure.entities.CustomerEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerDAO customerDAO;

    public void issueInvoice(CustomerEntity customer) {
        customerDAO.issueInvoice(customer);
    }

    public CustomerEntity findCustomer(String email) {
        Optional<CustomerEntity> customer = customerDAO.findByEmail(email);
        if (customer.isEmpty()) {
            throw new RuntimeException("Could not find customer by email: [%s]".formatted(email));
        }
        return customer.get();
    }

    public void saveCustomer(CustomerEntity customer) {
        customerDAO.saveCustomer(customer);
    }
}
