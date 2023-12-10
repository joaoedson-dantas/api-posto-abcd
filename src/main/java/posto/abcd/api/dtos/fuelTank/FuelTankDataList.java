package posto.abcd.api.dtos.fuelTank;

import posto.abcd.api.entity.fuelTank.FuelTankEntity;
import java.time.LocalDateTime;

public record FuelTankDataList(Long id, String name, Long liters, LocalDateTime created_at) {
    public FuelTankDataList(FuelTankEntity fuelTankEntity) {
        this(fuelTankEntity.getId(), fuelTankEntity.getName(), fuelTankEntity.getLiters(), fuelTankEntity.getCreated_at());
    }
}
