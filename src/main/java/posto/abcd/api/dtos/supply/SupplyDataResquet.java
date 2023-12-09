package posto.abcd.api.dtos.supply;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record SupplyDataResquet(


        @NotNull
        LocalDateTime date,

        @NotNull
        @Positive(message = "A quantidade de litros deve ser um número positivo")
        Long liters,

        @NotNull
        Long fuel_pomp_id

) {
}
