package pl.zajavka.business.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.DAO.CarServiceManagementDAO;
import pl.zajavka.business.services.utils.Keys;
import pl.zajavka.model.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
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

    // DWA RAZY PART !!!!!!!!!!!!!
    private void saveManagment(CarServiceManagement carServiceManagement) {
        CarToService carToService = carService.findCarToService(carServiceManagement.getCarVin()).orElseThrow();
        Mechanic mechanic = mechanicService.findMechanic(carServiceManagement.getMechanicPesel());
    //    Part part = partService.findPart(carServiceManagement.getPartSerialNumber()).orElse(null);
        CarServiceRequest carServiceRequest
                = carServiceRequestService.findActiveRequest(carServiceManagement.getCarVin());
        ServiceCatalog service = serviceCatalogService.findService(carServiceManagement.getServiceCode());
        ServiceMechanic serviceMechanic
                = buildServiceMechanic(carServiceManagement, mechanic, carServiceRequest, service);


        if (Keys.Properties.FINISHED.toString().equals(carServiceManagement.getDone())) {
            carServiceRequest = carServiceRequest.withCompletedDateTime(OffsetDateTime.now());
//            carServiceRequest.setCompletedDateTime(OffsetDateTime.now());
        }
        if (Objects.isNull(carServiceManagement.getPartSerialNumber()) || Objects.isNull(carServiceManagement.getPartQuantity())) {
            carServiceManagementDAO.manage(carServiceRequest, serviceMechanic);
        } else {
            Part part = partService.findPart(carServiceManagement.getPartSerialNumber()).orElseThrow();
            ServicePart servicePart = buildServicePart(carServiceManagement, carServiceRequest, part);
            carServiceManagementDAO.manage(carServiceRequest, serviceMechanic, servicePart);
        }

    }

    private CarServiceManagement createListOfManagements(Map<String, List<String>> input) {
        List<String> whats = input.get(Keys.Properties.WHAT.toString());
        return CarServiceManagement.builder()
                .mechanicPesel(input.get(Keys.Domains.MECHANIC.toString()).get(0))
                .carVin(input.get(Keys.Domains.CAR.toString()).get(0))
                .partSerialNumber(Optional.ofNullable(whats.get(0)).filter(value -> !value.isBlank()).orElse(null))
                .partQuantity(Optional.ofNullable(whats.get(1)).filter(value -> !value.isBlank()).map(Integer::parseInt).orElse(null))
                .serviceCode(whats.get(2))
                .hours(Integer.parseInt(whats.get(3)))
                .comment(whats.get(4))
                .done(whats.get(5))
                .build();
    }

    private ServiceMechanic buildServiceMechanic(
            CarServiceManagement request,
            Mechanic mechanic,
            CarServiceRequest serviceRequest,
            ServiceCatalog serviceCatalog
    ) {
        return ServiceMechanic.builder()
                .hours(request.getHours())
                .comment(request.getComment())
                .carServiceRequest(serviceRequest)
                .mechanic(mechanic)
                .serviceCatalog(serviceCatalog)
                .build();
    }

    private ServicePart buildServicePart(
            CarServiceManagement request,
            CarServiceRequest serviceRequest,
            Part part
    ) {
        return ServicePart.builder()
                .quantity(request.getPartQuantity())
                .carServiceRequest(serviceRequest)
                .part(part)
                .build();
    }
}
