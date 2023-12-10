package posto.abcd.api.entity.user;

import jakarta.persistence.*;
import lombok.*;
import posto.abcd.api.dtos.user.UserDataRequest;


@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {

    @Id // informa para JPA que vai ser o ID da entidade
        @GeneratedValue(strategy = GenerationType.IDENTITY) // estratégia de geracao de id
    private Long id;
    private String name;
    private String login;
    @Setter
    private String password_hash;

    public UserEntity(UserDataRequest userData) {
        this.name = userData.name();
        this.login = userData.login();
        this.password_hash = userData.password_hash();
    }
}
