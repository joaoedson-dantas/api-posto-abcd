package posto.abcd.api.dtos.user;

import posto.abcd.api.entity.user.UserEntity;

public record UserDataResponse(Long id, String name, String login) {
    public UserDataResponse(UserEntity user) {
        this(user.getId(), user.getName(), user.getLogin());
    }
}
