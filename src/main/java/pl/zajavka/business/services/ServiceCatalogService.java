package pl.zajavka.business.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.ServiceCatalogDAO;
import pl.zajavka.model.ServiceCatalog;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceCatalogService {

    private ServiceCatalogDAO serviceCatalogDAO;

    public ServiceCatalog findService(String serviceCode) {
        Optional<ServiceCatalog> service = serviceCatalogDAO.findServiceByServiceCode(serviceCode);
        if (service.isEmpty()) {
            throw new RuntimeException("Could not find service by service code: [%s]".formatted(serviceCode));
        }
        return service.get();
    }
}
