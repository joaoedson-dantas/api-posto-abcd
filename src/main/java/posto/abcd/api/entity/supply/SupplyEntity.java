package posto.abcd.api.entity.supply;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;
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
    private Long price;

    @Setter
    @Min(0)
    private int tax;


    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_pomp_id")
    private FuelPumpEntity fuelPumpEntity;



    public SupplyEntity(LocalDateTime date, Long liters, long price, int taxAmount, FuelPumpEntity fuelPumpEntity) {
        System.out.println("teste 1 -> " + fuelPumpEntity.getId());
        System.out.println("Teste 2 -> " + fuelPumpEntity.getName());

        this.setDate(date);
        this.setLiters(liters);
        this.setPrice(price);
        this.setTax(taxAmount);
        this.setFuelPumpEntity(fuelPumpEntity);

    }


}
