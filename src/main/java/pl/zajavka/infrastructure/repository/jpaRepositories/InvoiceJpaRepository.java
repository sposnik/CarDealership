package pl.zajavka.infrastructure.repository.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.infrastructure.entities.InvoiceEntity;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, Integer> {

}
