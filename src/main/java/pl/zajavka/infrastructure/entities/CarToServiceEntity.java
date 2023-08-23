package pl.zajavka.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "car_to_service")
@Getter
@Setter
@EqualsAndHashCode(of = {"carToServiceId", "vin"})
@ToString(of = {"carToServiceId", "vin", "brand", "model", "year"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarToServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_to_service_id")
    private Integer carToServiceId;

    @Column(name = "vin", unique = true)
    private String vin;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private Set<CarServiceRequestEntity> carServiceRequests;

    public void addServiceRequest(CarServiceRequestEntity carServiceRequestEntity) {
        if (Objects.isNull(carServiceRequests)) {
            this.carServiceRequests = new HashSet<>();
        }
        carServiceRequests.add(carServiceRequestEntity);
    }
}
