package pl.zajavka.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_part")
@Getter
@Setter
@EqualsAndHashCode(of = "servicePartId")
@ToString(of = {"servicePartId", "quantity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicePartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_part_id")
    private Integer servicePartId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_service_request_id")
    private CarServiceRequestEntity carServiceRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id")
    private PartEntity part;
}
