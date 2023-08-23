package pl.zajavka.business.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.PartDAO;
import pl.zajavka.model.Part;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PartService {

    private PartDAO partDAO;


    public Optional<Part> findPart(String serialNumber) {
        return partDAO.findBySerialNumber(serialNumber);

    }

}
