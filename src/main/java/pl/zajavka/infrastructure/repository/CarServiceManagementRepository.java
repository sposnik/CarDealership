package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.DAO.CarServiceManagementDAO;
import pl.zajavka.infrastructure.entities.CarServiceRequestEntity;
import pl.zajavka.infrastructure.entities.PartEntity;
import pl.zajavka.infrastructure.entities.ServiceMechanicEntity;
import pl.zajavka.infrastructure.entities.ServicePartEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarServiceRequestJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.PartJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.ServiceMechanicJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.ServicePartJpaRepository;
import pl.zajavka.infrastructure.repository.mapper.CarServiceRequestEntityMapper;
import pl.zajavka.infrastructure.repository.mapper.ServiceMechanicEntityMapper;
import pl.zajavka.infrastructure.repository.mapper.ServicePartEntityMapper;
import pl.zajavka.model.CarServiceRequest;
import pl.zajavka.model.ServiceMechanic;
import pl.zajavka.model.ServicePart;

import java.util.Objects;

@Repository
@AllArgsConstructor
public class CarServiceManagementRepository implements CarServiceManagementDAO {


    private ServiceMechanicJpaRepository serviceMechanicJpaRepository;

    private CarServiceRequestJpaRepository carServiceRequestJpaRepository;

    private ServicePartJpaRepository servicePartJpaRepository;

    private ServiceMechanicEntityMapper serviceMechanicEntityMapper;

    private ServicePartEntityMapper servicePartEntityMapper;

    private PartJpaRepository partJpaRepository;


    @Override
    @Transactional
    public void manage(CarServiceRequest carServiceRequest, ServiceMechanic serviceMechanic) {

        ServiceMechanicEntity serviceMechanicEntity = serviceMechanicEntityMapper.mapToEntity(serviceMechanic);
        serviceMechanicJpaRepository.saveAndFlush(serviceMechanicEntity);
        if (Objects.nonNull(carServiceRequest.getCompletedDateTime())) {
            CarServiceRequestEntity carServiceRequestEntity = carServiceRequestJpaRepository
                    .findById(carServiceRequest.getCarServiceRequestId())
                    .orElseThrow();
            carServiceRequestEntity.setCompletedDateTime(carServiceRequest.getCompletedDateTime());
            carServiceRequestJpaRepository.saveAndFlush(carServiceRequestEntity);
        }
    }

    @Override
    @Transactional
    public void manage(CarServiceRequest carServiceRequest,
                       ServiceMechanic serviceMechanic,
                       ServicePart servicePart) {

        PartEntity partEntity = partJpaRepository.findById(servicePart.getPart().getPartId()).orElseThrow();
        ServicePartEntity servicePartEntity = servicePartEntityMapper.mapToEntity(servicePart);
        servicePartEntity.setPart(partEntity);
        servicePartJpaRepository.saveAndFlush(servicePartEntity);
        manage(carServiceRequest, serviceMechanic);
    }

}
