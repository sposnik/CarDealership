package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.DAO.CustomerDAO;
import pl.zajavka.infrastructure.entities.CustomerEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CustomerJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.InvoiceJpaRepository;

import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDAO {

    private CustomerJpaRepository customerJpaRepository;

    private InvoiceJpaRepository invoiceJpaRepository;

    @Override
    public void saveCustomer(CustomerEntity customer) {
        customerJpaRepository.save(customer);
    }

    @Override
    public Optional<CustomerEntity> findByEmail(String email) {
        return customerJpaRepository.findByEmail(email);
//                .map(customerEntityMapper::mapFromEntity);
    }

    @Override
    public void issueInvoice(CustomerEntity customer) {

                customerJpaRepository.saveAndFlush(customer);

            customer.getInvoices().stream()
                    .filter(invoice -> Objects.isNull(invoice.getInvoiceId()))
                    .forEach(invoice -> {
                        invoice.setCustomer(customer);
                        invoiceJpaRepository.saveAndFlush(invoice);
                    });
    }
}
