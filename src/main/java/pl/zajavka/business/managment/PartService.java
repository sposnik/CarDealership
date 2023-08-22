package pl.zajavka.business.managment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.PartDAO;
import pl.zajavka.infrastructure.entities.PartEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PartService {

    private PartDAO partDAO;


    public Optional<PartEntity> findPart(String serialNumber) {
        return partDAO.findBySerialNumber(serialNumber);

    }

}
