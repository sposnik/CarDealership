package pl.zajavka.model;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "invoiceNumber")
@ToString(of = {"invoiceId", "invoiceNumber", "dateTime"})
public class Invoice {

    Integer invoiceId;
    String invoiceNumber;
    OffsetDateTime dateTime;
    CarToBuy car;
    Customer customer;
    Salesman salesman;
}
