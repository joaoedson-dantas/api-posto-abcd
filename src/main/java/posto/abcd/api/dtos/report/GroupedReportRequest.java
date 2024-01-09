package posto.abcd.api.dtos.report;

import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public record GroupedReportRequest(@Nullable LocalDateTime dataInical, @Nullable LocalDateTime dataFinal) {
}
