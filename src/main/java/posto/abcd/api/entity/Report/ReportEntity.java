package posto.abcd.api.entity.Report;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity()
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @NotNull
    private LocalDate data;

    @Setter @NotNull
    private String fuelTankName;

    @Setter @NotNull
    private String fuelPumpName;

    @Setter @NotNull
    private String fuelType;

    @Setter @NotNull
    private BigDecimal liters;

    @Setter @NotNull
    private BigDecimal totalPrice;
}
