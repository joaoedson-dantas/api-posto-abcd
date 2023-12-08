package posto.abcd.api.dtos.fuelPump;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuelPumpDataRequest(
        @NotBlank
        String name,

         @NotNull(message = "O ID do tanque de combustível não pode ser nulo")
                 Long fuel_tank_id
) {
}
