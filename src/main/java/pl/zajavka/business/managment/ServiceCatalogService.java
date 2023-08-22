package pl.zajavka.business.managment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.ServiceCatalogDAO;
import pl.zajavka.infrastructure.entities.ServiceCatalogEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceCatalogService {

    private ServiceCatalogDAO serviceCatalogDAO;

    public ServiceCatalogEntity findService(String serviceCode) {
        Optional<ServiceCatalogEntity> service = serviceCatalogDAO.findServiceByServiceCode(serviceCode);
        if (service.isEmpty()) {
            throw new RuntimeException("Could not find service by service code: [%s]".formatted(serviceCode));
        }
        return service.get();
    }
}
