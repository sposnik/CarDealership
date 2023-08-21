package pl.zajavka.managment;

import lombok.AllArgsConstructor;
import pl.zajavka.DAO.SalesmanDAO;
import pl.zajavka.model.entities.SalesmanEntity;

import java.util.Optional;

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
