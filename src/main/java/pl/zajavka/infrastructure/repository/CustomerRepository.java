package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.CustomerDAO;
import pl.zajavka.infrastructure.entities.CustomerEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CustomerJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.InvoiceJpaRepository;
import pl.zajavka.infrastructure.repository.mapper.CustomerEntityMapper;
import pl.zajavka.infrastructure.repository.mapper.InvoiceEntityMapper;
import pl.zajavka.model.Customer;

import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDAO {

    private CustomerJpaRepository customerJpaRepository;
    private InvoiceJpaRepository invoiceJpaRepository;
    private CustomerEntityMapper customerEntityMapper;
    private InvoiceEntityMapper invoiceEntityMapper;

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity saved = customerJpaRepository.saveAndFlush(customerEntityMapper.mapToEntity(customer));
        return customerEntityMapper.mapFromEntity(saved);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerJpaRepository.findByEmail(email)
                .map(customerEntityMapper::mapFromEntity);
    }

    @Override
    public void issueInvoice(Customer customer) {

        CustomerEntity customerSaved
                = customerJpaRepository.saveAndFlush(customerEntityMapper.mapToEntity(customer));

        customer.getInvoices().stream()
                .filter(invoice -> Objects.isNull(invoice.getInvoiceId()))
                .map(invoiceEntityMapper::mapToEntity)
                .forEach(invoiceEntity -> {
                    invoiceEntity.setCustomer(customerSaved);
                    invoiceJpaRepository.saveAndFlush(invoiceEntity);
                });
    }
}
