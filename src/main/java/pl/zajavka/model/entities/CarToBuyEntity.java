package pl.zajavka.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "car_to_buy")
@Getter
@Setter
@EqualsAndHashCode(of = "carToBuyId")
@ToString(of = {"carToBuyId", "vin", "brand", "model", "year", "color", "price"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarToBuyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_to_buy_id")
    private Integer carToBuyId;

    @Column(name = "vin", unique = true)
    private String vin;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "car", cascade = CascadeType.ALL)
    private InvoiceEntity invoice;
}




