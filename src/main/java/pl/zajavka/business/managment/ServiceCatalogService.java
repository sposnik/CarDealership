package pl.zajavka.business.managment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.ServiceDAO;
import pl.zajavka.infrastructure.entities.ServiceEntity;

import java.util.Optional;

@Service
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
