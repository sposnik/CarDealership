package pl.zajavka.managment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.DAO.MechanicDAO;
import pl.zajavka.model.entities.CustomerEntity;
import pl.zajavka.model.entities.MechanicEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MechanicService {

    private MechanicDAO mechanicDAO;


    public MechanicEntity findMechanic(String pesel) {
        Optional<MechanicEntity> mechanic = mechanicDAO.findByPesel(pesel);
        if (mechanic.isEmpty()) {
            throw new RuntimeException("Could not find mechanic by pesel: [%s]".formatted(pesel));
        }
        return mechanic.get();
    }
}
