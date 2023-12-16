package posto.abcd.api.dtos.user;

import posto.abcd.api.entity.user.UserEntity;

public record AuthUserDataResponse(Long id, String name, String login, String token) {
    public AuthUserDataResponse(UserEntity user, String token) {
        this(user.getId(), user.getName(), user.getLogin(), token);
    }
}
