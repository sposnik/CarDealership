package pl.zajavka.business.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.MechanicDAO;
import pl.zajavka.model.Mechanic;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MechanicService {

    private MechanicDAO mechanicDAO;


    public Mechanic findMechanic(String pesel) {
        Optional<Mechanic> mechanic = mechanicDAO.findByPesel(pesel);
        if (mechanic.isEmpty()) {
            throw new RuntimeException("Could not find mechanic by pesel: [%s]".formatted(pesel));
        }
        return mechanic.get();
    }
}
