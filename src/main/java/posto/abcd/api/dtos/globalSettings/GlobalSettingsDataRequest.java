package posto.abcd.api.dtos.globalSettings;

import jakarta.validation.constraints.NotBlank;

public record GlobalSettingsDataRequest(

        @NotBlank
        String label,
        @NotBlank
        String key,
        @NotBlank
        String value) {
}
