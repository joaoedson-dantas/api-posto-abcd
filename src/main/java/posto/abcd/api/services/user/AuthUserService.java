package posto.abcd.api.services.user;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import posto.abcd.api.dtos.user.AuthUserDTO;
import posto.abcd.api.repository.user.UserRepository;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthUserService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthUserDTO authUserDTO) throws AuthenticationException {
        var user = this.userRepository.findByLogin(authUserDTO.login()).orElseThrow(
                () -> new UsernameNotFoundException("Usuário não encontrado")
        );

        var passwordMatches = this.passwordEncoder.matches(authUserDTO.password_hash(), user.getPassword_hash());

        if (!passwordMatches) {
           throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer(user.getName())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(user.getId().toString())
                .sign(algorithm);

        return token;
    }
}
