package posto.abcd.api.dtos.fillTank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import posto.abcd.api.entity.fuelTank.FuelTankEntity;

import java.time.LocalDateTime;

public record FillTankDataRequest(
        @Positive(message = "A quantidade de litros deve ser um número positivo")
        Long liters,

        @NotNull(message = "O ID do tanque de combustível não pode ser nulo")
        Long fuel_tank_id
) {}
