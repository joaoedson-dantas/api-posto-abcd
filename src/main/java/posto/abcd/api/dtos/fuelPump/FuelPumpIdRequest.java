package posto.abcd.api.dtos.fuelPump;

import jakarta.validation.constraints.NotNull;

public record FuelPumpIdRequest(

        @NotNull
        Long id
) {
}
