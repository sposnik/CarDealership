package pl.zajavka.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.DAO.CarServiceManagementDAO;
import pl.zajavka.infrastructure.entities.CarServiceRequestEntity;
import pl.zajavka.infrastructure.entities.ServiceMechanicEntity;
import pl.zajavka.infrastructure.entities.ServicePartEntity;
import pl.zajavka.infrastructure.repository.jpaRepositories.CarServiceRequestJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.ServiceMechanicJpaRepository;
import pl.zajavka.infrastructure.repository.jpaRepositories.ServicePartJpaRepository;

import java.util.Objects;

@Repository
@AllArgsConstructor
public class CarServiceManagementRepository implements CarServiceManagementDAO {


    private ServiceMechanicJpaRepository serviceMechanicJpaRepository;

    private CarServiceRequestJpaRepository carServiceRequestJpaRepository;

    private ServicePartJpaRepository servicePartJpaRepository;


    @Override
    @Transactional
    public void manage(CarServiceRequestEntity carServiceRequest, ServiceMechanicEntity serviceMechanicEntity) {

        serviceMechanicJpaRepository.saveAndFlush(serviceMechanicEntity);

        if (!Objects.isNull(carServiceRequest.getCompletedDateTime())) {
            carServiceRequestJpaRepository.saveAndFlush(carServiceRequest);
            }

    }

    @Override
    @Transactional
    public void manage(CarServiceRequestEntity carServiceRequest,
                       ServiceMechanicEntity serviceMechanicEntity,
                       ServicePartEntity servicePartEntity) {

        serviceMechanicJpaRepository.saveAndFlush(serviceMechanicEntity);
        servicePartJpaRepository.saveAndFlush(servicePartEntity);
        if (!Objects.isNull(carServiceRequest.getCompletedDateTime())) {
            carServiceRequestJpaRepository.saveAndFlush(carServiceRequest);
        }
    }
}
