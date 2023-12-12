package posto.abcd.api.services.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.user.UserDataRequest;
import posto.abcd.api.entity.user.UserEntity;
import posto.abcd.api.repository.user.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Test
    @DisplayName("It should be possible to create a user with password hash")
    void createSceneOne() {
        // Arrange
        UserDataRequest userDataRequest = new UserDataRequest("joao dos testes", "joao.teste1", "123456");
        UserEntity userEntity = new UserEntity(userDataRequest);

        // Act
        var user = userService.create(userEntity);

        // Assert
        assertEquals("joao.teste1", user.getLogin());
        assertTrue(passwordEncoder.matches("123456", user.getPassword_hash()));
    }

    @Test
    @DisplayName("It should not be possible to register a user -> User Already Exists")
    void createSceneTwo() {
        // Arrange
        UserDataRequest userDataRequest = new UserDataRequest("dev.test3111", "dev.test99", "123456");
        UserEntity userEntity = new UserEntity(userDataRequest);

        // Act
        userService.create(userEntity);

        // Assert
        UserEntity duplicateUserEntity = new UserEntity(userDataRequest);
        assertThrows(Exception.class, () -> userService.create(duplicateUserEntity));
    }
}