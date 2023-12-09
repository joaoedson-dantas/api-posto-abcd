package posto.abcd.api.dtos.supply;

import java.time.LocalDateTime;

public record SupplyDataResponse(
        Long id,
        LocalDateTime date,
        Long liters,
        Long price,
        int tax,
        String fuel_pomp_id
) {
}
