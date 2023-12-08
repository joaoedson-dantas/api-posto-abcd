package posto.abcd.api.dtos.fuelTank;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FuelTankDataRequest(

        @NotBlank(message = "O nome do tipo de combustível não pode ser nulo")
        String name,

        @Positive(message = "A quantidade de litros deve ser um número positivo")
        Long liters
) {}
