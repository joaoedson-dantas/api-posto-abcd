package posto.abcd.api.service.user;


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
                () -> {
                    throw new UsernameNotFoundException("Usuário não encontrado");
                }
        );

        var passwordMatches = this.passwordEncoder.matches(authUserDTO.password(), user.getPassword_hash());
        if (!passwordMatches) {
           throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer(user.getName())
                .withSubject(user.getId().toString())
                .sign(algorithm);

        return token;
    }
}
