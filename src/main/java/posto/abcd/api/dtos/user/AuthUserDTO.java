package posto.abcd.api.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record AuthUserDTO (

        @NotBlank
        String password_hash,
        @NotBlank
        String login
) {}
