package posto.abcd.api.services.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import posto.abcd.api.dtos.user.AuthUserDTO;
import posto.abcd.api.dtos.user.UserDataRequest;
import posto.abcd.api.entity.user.UserEntity;
import posto.abcd.api.repository.user.UserRepository;

import javax.naming.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
class AuthUserServiceTest {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthUserService authUserService;

    @Test
    @DisplayName("A JWT Token should be returned")
    void executeSceneOne() throws AuthenticationException {
        // Arrange
        UserDataRequest userDataRequest = new UserDataRequest("dev test", "dev.test1", "123456");
        UserEntity userEntity = new UserEntity(userDataRequest);
        var user = userService.create(userEntity);

        AuthUserDTO authUserDTO = new AuthUserDTO("dev.test1", "123456");

        // Act
        var token = authUserService.execute(authUserDTO);

        // Assert
        assertNotNull(token, "O token não deveria ser nulo");
        assertFalse(token.isEmpty(), "O token deveria ter um comprimento maior que 0");
    }

    @Test
    @DisplayName("It should not be possible to authenticate")
    void executeSceneTwo() throws AuthenticationException {
        // Arrange
        UserDataRequest userDataRequest = new UserDataRequest("dev test", "dev.test1", "123456");
        UserEntity userEntity = new UserEntity(userDataRequest);
        var user = userService.create(userEntity);

        AuthUserDTO authUserDTO = new AuthUserDTO("dev.test1", "senhaErrada");

        // Assert

        assertThrows(Exception.class, () -> authUserService.execute(authUserDTO));
    }
}