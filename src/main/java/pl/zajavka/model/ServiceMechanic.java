package pl.zajavka.model;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "serviceMechanicId")
@ToString(of = {"serviceMechanicId", "hours", "comment"})
public class ServiceMechanic {

    Integer serviceMechanicId;
    Integer hours;
    String comment;
    CarServiceRequest carServiceRequest;
    Mechanic mechanic;
    ServiceCatalog serviceCatalog;

}
