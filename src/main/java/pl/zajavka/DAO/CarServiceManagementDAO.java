package pl.zajavka.DAO;

import pl.zajavka.model.entities.CarServiceRequestEntity;
import pl.zajavka.model.entities.ServiceMechanicEntity;
import pl.zajavka.model.entities.ServicePartEntity;

public interface CarServiceManagementDAO {
    void manage(CarServiceRequestEntity carServiceRequest, ServiceMechanicEntity serviceMechanicEntity);

    void manage(CarServiceRequestEntity carServiceRequest, ServiceMechanicEntity serviceMechanicEntity, ServicePartEntity servicePartEntity);
}
