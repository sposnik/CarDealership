package pl.zajavka.managment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.DAO.MechanicDAO;
import pl.zajavka.DAO.PartDAO;
import pl.zajavka.model.entities.MechanicEntity;
import pl.zajavka.model.entities.PartEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PartService {

    private PartDAO partDAO;


    public Optional<PartEntity> findPart(String serialNumber) {
        return partDAO.findBySerialNumber(serialNumber);

    }

}
