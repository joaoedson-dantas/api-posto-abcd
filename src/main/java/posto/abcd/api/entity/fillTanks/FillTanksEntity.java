package posto.abcd.api.entity.fillTanks;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import posto.abcd.api.dtos.fillTank.FillTankDataRequest;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "fillTanks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FillTanksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;


    @Min(0)
    private Long liters;

    @ManyToOne
    @JoinColumn(name = "fuel_tank_id")
    private FuelTankEntity fuelTankEntity;

}
