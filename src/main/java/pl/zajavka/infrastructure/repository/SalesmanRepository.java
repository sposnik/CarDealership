package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.SalesmanDAO;
import pl.zajavka.infrastructure.entities.SalesmanEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.SalesmanJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SalesmanRepository implements SalesmanDAO {

    @Autowired
    private SalesmanJpaRepository salesmanJpaRepository;

    @Override
    public Optional<SalesmanEntity> findByPesel(String pesel) {
        return salesmanJpaRepository.findByPesel(pesel);
    }
}
