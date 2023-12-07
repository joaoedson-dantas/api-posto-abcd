package posto.abcd.api.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDataRequest(

        @NotBlank
        String name,
        @Pattern(regexp = "^\\S+$", message = "O campo [login] não deve conter espaços em branco")
        String login,
        @NotBlank
        String password_hash

) {}
