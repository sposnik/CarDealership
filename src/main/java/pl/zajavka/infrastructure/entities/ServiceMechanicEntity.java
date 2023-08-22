package pl.zajavka.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_mechanic")
@Getter
@Setter
@EqualsAndHashCode(of = "serviceMechanicId")
@ToString(of = {"serviceMechanicId", "hours", "comment"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceMechanicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_mechanic_id")
    private Integer serviceMechanicId;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_service_request_id")
    private CarServiceRequestEntity carServiceRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mechanic_id")
    private MechanicEntity mechanic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private ServiceCatalogEntity service;
}
