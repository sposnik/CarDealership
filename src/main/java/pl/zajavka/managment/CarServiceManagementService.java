package pl.zajavka.managment;

import lombok.AllArgsConstructor;
import pl.zajavka.DAO.CarServiceManagementDAO;
import pl.zajavka.model.entities.*;
import pl.zajavka.records.CarServiceManagement;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
public class CarServiceManagementService {

    private DataPrepareService dataPrepareService;

    private CarServiceManagementDAO carServiceManagementDAO;

    private CarService carService;

    private CarServiceRequestService carServiceRequestService;

    private MechanicService mechanicService;

    private PartService partService;

    private ServiceCatalogService serviceCatalogService;


    public void processService() {
        var toManage = dataPrepareService.mapOfProcessingService();
        List<CarServiceManagement> listOfServiceManagements = toManage.stream()
                .map(this::createListOfManagements).toList();
        listOfServiceManagements.forEach(this::saveManagment);
    }

    private void saveManagment(CarServiceManagement carServiceManagement) {
        CarToServiceEntity carToServiceEntity = carService.findCarToService(carServiceManagement.getCarVin()).orElseThrow();
        MechanicEntity mechanicEntity = mechanicService.findMechanic(carServiceManagement.getMechanicPesel());
        PartEntity partEntity = partService.findPart(carServiceManagement.getPartSerialNumber()).orElse(null);
        CarServiceRequestEntity carServiceRequest
                = carServiceRequestService.findActiveRequest(carServiceManagement.getCarVin());
        ServiceEntity service = serviceCatalogService.findService(carServiceManagement.getServiceCode());
        ServiceMechanicEntity serviceMechanicEntity
                = buildServiceMechanicEntity(carServiceManagement, mechanicEntity, carServiceRequest, service);


        if (Keys.Properties.FINISHED.toString().equals(carServiceManagement.getDone())) {
            carServiceRequest.setCompletedDateTime(OffsetDateTime.now());
        }
        if (Objects.isNull(carServiceManagement.getPartSerialNumber()) || Objects.isNull(carServiceManagement.getPartQuantity())) {
            carServiceManagementDAO.manage(carServiceRequest, serviceMechanicEntity);
        } else {
            PartEntity part = partService.findPart(carServiceManagement.getPartSerialNumber()).orElseThrow();
            ServicePartEntity servicePartEntity = buildServicePartEntity(carServiceManagement, carServiceRequest, part);
            carServiceManagementDAO.manage(carServiceRequest, serviceMechanicEntity, servicePartEntity);
        }

    }

    private CarServiceManagement createListOfManagements(Map<String, List<String>> input) {
        List<String> whats = input.get(Keys.Properties.WHAT.toString());
        return CarServiceManagement.builder()
                .mechanicPesel(input.get(Keys.Entity.MECHANIC.toString()).get(0))
                .carVin(input.get(Keys.Entity.CAR.toString()).get(0))
                .partSerialNumber(Optional.ofNullable(whats.get(0)).filter(value -> !value.isBlank()).orElse(null))
                .partQuantity(Optional.ofNullable(whats.get(1)).filter(value -> !value.isBlank()).map(Integer::parseInt).orElse(null))
                .serviceCode(whats.get(2))
                .hours(Integer.parseInt(whats.get(3)))
                .comment(whats.get(4))
                .done(whats.get(5))
                .build();
    }

    private ServiceMechanicEntity buildServiceMechanicEntity(
            CarServiceManagement request,
            MechanicEntity mechanic,
            CarServiceRequestEntity serviceRequest,
            ServiceEntity service
    ) {
        return ServiceMechanicEntity.builder()
                .hours(request.getHours())
                .comment(request.getComment())
                .carServiceRequest(serviceRequest)
                .mechanic(mechanic)
                .service(service)
                .build();
    }

    private ServicePartEntity buildServicePartEntity(
            CarServiceManagement request,
            CarServiceRequestEntity serviceRequest,
            PartEntity part
    ) {
        return ServicePartEntity.builder()
                .quantity(request.getPartQuantity())
                .carServiceRequest(serviceRequest)
                .part(part)
                .build();
    }
}
