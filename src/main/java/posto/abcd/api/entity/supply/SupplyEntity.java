package posto.abcd.api.entity.supply;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "SupplyEntity")
@Table(name = "supplies")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SupplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "date")
    private LocalDateTime date;

    @Setter
    @Min(0)
    private Long liters;

    @Setter
    @Min(0)
    private BigDecimal price;

    @Setter
    @Min(0)
    private int tax;


    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_pomp_id")
    private FuelPumpEntity fuelPumpEntity;



    public SupplyEntity(LocalDateTime date, Long liters, BigDecimal price, int taxAmount, FuelPumpEntity fuelPumpEntity) {

        this.setDate(date);
        this.setLiters(liters);
        this.setPrice(price);
        this.setTax(taxAmount);
        this.setFuelPumpEntity(fuelPumpEntity);

    }


}
