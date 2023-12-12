package posto.abcd.api.dtos.supply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SupplyDataResquet(


        @NotNull
        LocalDateTime date,

        @NotNull
        @Positive(message = "A quantidade de litros deve ser um número positivo")
        BigDecimal liters,

        @NotNull
        Long fuel_pomp_id,

        @NotBlank(message = "Insira a chave do tipo de combustível")
        String fuel_key

) {
}
