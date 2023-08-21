package pl.zajavka.records;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class CarServiceManagement {

    String mechanicPesel;
    String carVin;
    String partSerialNumber;
    Integer partQuantity;
    String serviceCode;
    Integer hours;
    String comment;
    String done;

}
