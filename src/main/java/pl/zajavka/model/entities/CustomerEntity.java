package pl.zajavka.model.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@EqualsAndHashCode(of = "customerId")
@ToString(of = {"customerId", "name", "surname", "phone", "email"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private AddressEntity address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private Set<InvoiceEntity> invoices;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private Set<CarServiceRequestEntity> carServiceRequests;

    public void addServiceRequest(CarServiceRequestEntity carServiceRequestEntity) {
        if (Objects.isNull(carServiceRequests)) {
            this.carServiceRequests = new HashSet<>();
        }
        carServiceRequests.add(carServiceRequestEntity);
    }
}
