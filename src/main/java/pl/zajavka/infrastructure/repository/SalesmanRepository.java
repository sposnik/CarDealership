package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.SalesmanDAO;
import pl.zajavka.infrastructure.repository.jpaRepositories.SalesmanJpaRepository;
import pl.zajavka.infrastructure.repository.mapper.SalesmanEntityMapper;
import pl.zajavka.model.Salesman;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SalesmanRepository implements SalesmanDAO {

    private SalesmanJpaRepository salesmanJpaRepository;

    private SalesmanEntityMapper salesmanEntityMapper;

    @Override
    public Optional<Salesman> findByPesel(String pesel) {
        return salesmanJpaRepository.findByPesel(pesel)
                .map(salesmanEntityMapper::mapFromEntity);
    }
}
