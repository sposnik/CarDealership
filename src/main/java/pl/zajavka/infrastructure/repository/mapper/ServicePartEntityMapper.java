package pl.zajavka.infrastructure.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.infrastructure.entities.ServicePartEntity;
import pl.zajavka.model.ServicePart;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServicePartEntityMapper {
    ServicePartEntity mapToEntity(ServicePart servicePart);
}
