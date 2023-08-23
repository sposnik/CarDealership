package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.DAO.PartDAO;
import pl.zajavka.infrastructure.repository.jpaRepositories.PartJpaRepository;
import pl.zajavka.infrastructure.repository.mapper.PartEntityMapper;
import pl.zajavka.model.Part;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PartRepository implements PartDAO {

    private PartJpaRepository partJpaRepository;
    private PartEntityMapper partEntityMapper;

    @Override
    public Optional<Part> findBySerialNumber(String serialNumber) {
        return partJpaRepository.findBySerialNumber(serialNumber)
                .map(partEntityMapper::mapFromEntity);
    }
}
