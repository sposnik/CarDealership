package pl.zajavka.model.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "address")
@Getter
@Setter
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"addressId", "country", "city", "postalCode", "address"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
    private CustomerEntity customer;
}
