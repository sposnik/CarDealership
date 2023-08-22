package pl.zajavka.business.DAO;

import pl.zajavka.infrastructure.entities.CarServiceRequestEntity;
import pl.zajavka.infrastructure.entities.ServiceMechanicEntity;
import pl.zajavka.infrastructure.entities.ServicePartEntity;

public interface CarServiceManagementDAO {
    void manage(CarServiceRequestEntity carServiceRequest, ServiceMechanicEntity serviceMechanicEntity);

    void manage(CarServiceRequestEntity carServiceRequest, ServiceMechanicEntity serviceMechanicEntity, ServicePartEntity servicePartEntity);
}
