package posto.abcd.api.dtos.supply;

import posto.abcd.api.entity.supply.SupplyEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SupplyDataResponse(
        Long id,
        LocalDateTime date,
        BigDecimal liters,
        BigDecimal price,
        int tax,
        Long fuel_pomp_id
) {
    public SupplyDataResponse(SupplyEntity supplyEntity) {
        this(supplyEntity.getId(), supplyEntity.getDate(), supplyEntity.getLiters(), supplyEntity.getPrice(), supplyEntity.getTax(), supplyEntity.getFuelPumpEntity().getId());
    }
}
