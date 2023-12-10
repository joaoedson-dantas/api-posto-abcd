package posto.abcd.api.entity.fuelTank;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import posto.abcd.api.dtos.fuelPump.FuelPumpDataRequest;
import posto.abcd.api.dtos.fuelTank.FuelTankDataRequest;

import java.time.LocalDateTime;

@Entity(name = "FuelTankEntity")
@Table(name = "fuelTanks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class FuelTankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;

    @Min(0) // garantir que o tank nunca fique negativo.
    @Setter
    private Long liters;

    @Column(name = "created_at")
    @Setter
    private LocalDateTime created_at;

    public FuelTankEntity(FuelTankDataRequest fuelTankDataRequest, LocalDateTime dateTime) {
        if (fuelTankDataRequest.liters() > 0 && fuelTankDataRequest.name() != null) {
            this.setLiters(fuelTankDataRequest.liters());
            this.setName(fuelTankDataRequest.name().toUpperCase());
            this.setCreated_at(dateTime);
        }
    }



    public void toFuel(Long numberOfLitersToFill) {
        if (numberOfLitersToFill != null && numberOfLitersToFill > 0) {
            this.setLiters(this.getLiters() + numberOfLitersToFill);
        }
    }

    public void drain(Long numberOfLitersToDrain) {
        if (numberOfLitersToDrain != null && numberOfLitersToDrain > 0 && numberOfLitersToDrain < this.getLiters()) {
            this.setLiters(this.getLiters() - numberOfLitersToDrain);
        }
    }

}
