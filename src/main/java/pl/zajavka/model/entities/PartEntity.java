package pl.zajavka.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name = "part")
@Getter
@Setter
@EqualsAndHashCode(of = "partId")
@ToString(of = {"partId", "serialNumber", "description", "price"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id")
    private Integer partId;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "part")
    private Set<ServicePartEntity> serviceParts;
}
