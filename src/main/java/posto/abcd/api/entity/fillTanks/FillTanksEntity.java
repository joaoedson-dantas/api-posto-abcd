package posto.abcd.api.entity.fillTanks;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import posto.abcd.api.dtos.fillTank.FillTankDataRequest;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;

import java.time.LocalDateTime;

@Entity(name = "FillTanksEntity")
@Table(name = "filltanks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FillTanksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @Column(name = "date")
    private LocalDateTime date;


    @Min(0)
    @Setter
    private Long liters;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_tank_id")
    private FuelTankEntity fuelTankEntity;

    public FillTanksEntity(FillTankDataRequest fillTankDataRequest, LocalDateTime now, FuelTankEntity fuelTank) {
        if (fillTankDataRequest.liters() > 0 ) {
            this.setLiters(fillTankDataRequest.liters());
            this.setDate(now);
            this.setFuelTankEntity(fuelTank);
        }
    }
}
