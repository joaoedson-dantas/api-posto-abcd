package posto.abcd.api.service.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import posto.abcd.api.dtos.user.UserDataRequest;
import posto.abcd.api.entity.user.UserEntity;
import posto.abcd.api.repository.user.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    @DisplayName("It should be possible to create a user with password hash")
    void createSceneOne() {
        // Arrange
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDataRequest userDataRequest = new UserDataRequest("joao dos testes", "joao.teste", "123456");
        UserEntity userEntity = new UserEntity(userDataRequest);

        // Act
        var user = userService.create(userEntity);

        // Assert
        assertEquals("joao.teste", user.getLogin());
        assertTrue(passwordEncoder.matches("123456", user.getPassword_hash()));

    }
}