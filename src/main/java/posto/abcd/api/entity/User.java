package posto.abcd.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id // informa para JPA que vai ser o ID da entidade
        @GeneratedValue(strategy = GenerationType.IDENTITY) // estratégia de geracao de id
    private Long id;
    private String name;
    private String login;
    private String password_hash;
}
