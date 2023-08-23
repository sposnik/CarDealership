package pl.zajavka.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "serviceCode")
@ToString(of = {"serviceId", "serviceCode", "description", "price"})
public class ServiceCatalog {

    Integer serviceId;
    String serviceCode;
    String description;
    BigDecimal price;
    Set<ServiceMechanic> serviceMechanics;
}