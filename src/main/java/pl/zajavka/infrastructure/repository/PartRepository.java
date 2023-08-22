package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.PartDAO;
import pl.zajavka.infrastructure.entities.PartEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.PartJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PartRepository implements PartDAO {

    private PartJpaRepository partJpaRepository;
    @Override
    public Optional<PartEntity> findBySerialNumber(String serialNumber) {
        return partJpaRepository.findBySerialNumber(serialNumber);
    }
}
