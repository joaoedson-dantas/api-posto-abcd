package posto.abcd.api.dtos.report;


import java.time.LocalDateTime;


import java.math.BigDecimal;


public record GroupedReportDTO(
        LocalDateTime date,
        String fuelTankName,
        String fuelPumpName,
        String fuelType,
        BigDecimal liters,
        BigDecimal totalAmount
) {
}
