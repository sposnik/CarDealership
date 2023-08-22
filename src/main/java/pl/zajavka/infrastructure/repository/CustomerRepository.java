package pl.zajavka.infrastructure.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.CustomerDAO;
import pl.zajavka.infrastructure.configuration.HibernateUtil;
import pl.zajavka.infrastructure.entities.CustomerEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CustomerJpaRepository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class CustomerRepository implements CustomerDAO {

    private CustomerJpaRepository customerJpaRepository;

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
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            if (Objects.isNull(customer.getCustomerId())) {
                session.persist(customer);
            }

            customer.getInvoices().stream()
                    .filter(invoice -> Objects.isNull(invoice.getInvoiceId()))
                    .forEach(invoice -> {
                        invoice.setCustomer(customer);
                        session.persist(invoice);
                    });

            session.getTransaction().commit();
        }
    }
}
