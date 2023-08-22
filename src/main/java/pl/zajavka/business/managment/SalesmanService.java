package pl.zajavka.business.managment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.SalesmanDAO;
import pl.zajavka.infrastructure.entities.SalesmanEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SalesmanService {

    private SalesmanDAO salesmanDAO;

    public SalesmanEntity findSalesman(String pesel){
        Optional<SalesmanEntity> salesman = salesmanDAO.findByPesel(pesel);

        if (salesman.isEmpty()) {
            throw new RuntimeException("Could not find salesman by pesel: [%s]".formatted(pesel));
        }
        return salesman.get();
    }
}
