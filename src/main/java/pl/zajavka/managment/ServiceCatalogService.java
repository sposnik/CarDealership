package pl.zajavka.managment;

import lombok.AllArgsConstructor;
import pl.zajavka.DAO.ServiceDAO;
import pl.zajavka.model.entities.CustomerEntity;
import pl.zajavka.model.entities.ServiceEntity;

import java.util.Optional;

@AllArgsConstructor
public class ServiceCatalogService {

    private ServiceDAO serviceDAO;

    public ServiceEntity findService(String serviceCode) {
        Optional<ServiceEntity> service = serviceDAO.findServiceByServiceCode(serviceCode);
        if (service.isEmpty()) {
            throw new RuntimeException("Could not find service by service code: [%s]".formatted(serviceCode));
        }
        return service.get();
    }
}