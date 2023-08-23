package pl.zajavka.business.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.SalesmanDAO;
import pl.zajavka.model.Salesman;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SalesmanService {

    private SalesmanDAO salesmanDAO;

    public Salesman findSalesman(String pesel){
        Optional<Salesman> salesman = salesmanDAO.findByPesel(pesel);

        if (salesman.isEmpty()) {
            throw new RuntimeException("Could not find salesman by pesel: [%s]".formatted(pesel));
        }
        return salesman.get();
    }
}
