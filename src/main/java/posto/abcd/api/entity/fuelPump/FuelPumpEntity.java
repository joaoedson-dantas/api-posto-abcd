package posto.abcd.api.entity.fuelPump;


import jakarta.persistence.*;
import lombok.*;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataRequest;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;

@Entity(name = "FuelPumpEntity")
@Table(name = "fuelPumps")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FuelPumpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_tank_id")
    private FuelTankEntity fuelTankEntity;


    public FuelPumpEntity(FuelPumpDataRequest fuelPumpDataRequest, FuelTankEntity fuelTankEntity) {
        if (fuelPumpDataRequest.name() != null) {
            this.setName(fuelPumpDataRequest.name());
            this.setFuelTankEntity(fuelTankEntity);
        }
    }
}
