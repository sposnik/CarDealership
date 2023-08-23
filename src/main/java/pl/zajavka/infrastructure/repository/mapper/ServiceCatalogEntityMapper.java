package pl.zajavka.infrastructure.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.infrastructure.entities.ServiceCatalogEntity;
import pl.zajavka.model.ServiceCatalog;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceCatalogEntityMapper {

    @Mapping(target = "serviceMechanics", ignore = true)
    ServiceCatalog mapFromEntity(ServiceCatalogEntity entity);
}
