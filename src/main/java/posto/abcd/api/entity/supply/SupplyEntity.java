package posto.abcd.api.entity.supply;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import posto.abcd.api.dtos.supply.SupplyDataResquet;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "supplies")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fuel_pomp_id")
    private FuelPumpEntity fuelPumpEntity;


    public SupplyEntity(SupplyDataResquet supplyDataResquet, BigDecimal price, BigDecimal tax, FuelPumpEntity pump) {
        if(supplyDataResquet.liters() > 0 ) {
            this.setDate(supplyDataResquet.date());
            this.setLiters(supplyDataResquet.liters());
            this.setFuelPumpEntity(pump);
            this.setTax(tax.byteValueExact());
            this.setPrice(price);
        }
    }
}
