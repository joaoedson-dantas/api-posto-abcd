package posto.abcd.api.dtos.fuelPump;
import posto.abcd.api.entity.fuelPump.FuelPumpEntity;

public record FuelPumpDataResponse(Long id, String name, Long fuel_tank_id) {
    public FuelPumpDataResponse(FuelPumpEntity fuelPumpEntity) {
        this(fuelPumpEntity.getId(), fuelPumpEntity.getName(), fuelPumpEntity.getFuelTankEntity().getId());
    }
}
