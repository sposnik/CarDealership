package pl.zajavka.model.entities;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Builder
@Value
public class CarHistory2 {

    String carVin;
    Set<CarServiceRequestEntity> serviceRequests;
}
