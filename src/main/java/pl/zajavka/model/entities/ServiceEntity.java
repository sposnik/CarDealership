package pl.zajavka.model.entities;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name = "service")
@Getter
@Setter
@EqualsAndHashCode(of = "serviceId")
@ToString(of = {"serviceId", "serviceCode", "description", "price"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "service_code", nullable = false)
    private String serviceCode;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "service")
    private Set<ServiceMechanicEntity> serviceMechanics;

}
