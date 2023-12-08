package posto.abcd.api.dtos.fillTank;

import posto.abcd.api.entity.fillTanks.FillTanksEntity;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;

import java.time.LocalDateTime;

public record FillTankDataResponse(Long id, LocalDateTime date, Long liters, Long fuel_tank_id) {

    public FillTankDataResponse(FillTanksEntity fillTanksEntity) {
        this(fillTanksEntity.getId(), fillTanksEntity.getDate(), fillTanksEntity.getLiters(), fillTanksEntity.getFuelTankEntity().getId());
    }

}
