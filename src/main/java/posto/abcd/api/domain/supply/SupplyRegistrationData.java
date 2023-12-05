package posto.abcd.api.domain.supply;

import java.time.LocalDateTime;

public record SupplyRegistrationData(LocalDateTime date, Long bomb_id, FuelType fuelType, String quantity_liters, String value_supplied) {

}
