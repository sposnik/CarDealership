package pl.zajavka.business.DAO;

import pl.zajavka.model.CarServiceRequest;
import pl.zajavka.model.ServiceMechanic;
import pl.zajavka.model.ServicePart;

public interface CarServiceManagementDAO {
    void manage(CarServiceRequest carServiceRequest, ServiceMechanic serviceMechanic);

    void manage(CarServiceRequest carServiceRequest, ServiceMechanic serviceMechanic, ServicePart servicePart);
}
